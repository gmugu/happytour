package com.gmugu.happytour.presenter.impl;

import com.gmugu.happyhour.message.ScenicModel;
import com.gmugu.happyhour.message.TravelTeamModel;
import com.gmugu.happyhour.message.request.CreateTeamRequest;
import com.gmugu.happyhour.message.request.DeleteTeamRequest;
import com.gmugu.happyhour.message.request.GetScenicListRequest;
import com.gmugu.happyhour.message.request.GetTeamListRequest;
import com.gmugu.happyhour.message.request.GuideCmdRequest;
import com.gmugu.happyhour.message.request.JoinTeamRequest;
import com.gmugu.happyhour.message.request.OutTeamRequest;
import com.gmugu.happyhour.message.result.CreateTeamResult;
import com.gmugu.happyhour.message.result.DeleteTeamResult;
import com.gmugu.happyhour.message.result.GetScenicListResult;
import com.gmugu.happyhour.message.result.GetTeamListResult;
import com.gmugu.happyhour.message.result.GuideCmdResult;
import com.gmugu.happyhour.message.result.JoinTeamResult;
import com.gmugu.happyhour.message.result.OutTeamResult;
import com.gmugu.happytour.comment.assist.Checker;
import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.presenter.IRealTimePresenter;
import com.gmugu.happytour.user.User;
import com.gmugu.happytour.view.IRealTimeView;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mugu on 16-4-19 下午5:58.
 */
public class RealTimePresenterImpl implements IRealTimePresenter {

    private IRealTimeView view;
    private IApiService apiService;
    private List<ScenicModel> scenicModels;
    private ScenicModel scenicModel;
    private List<TravelTeamModel> travelTeamModels;
    private int cmdFlag = -1;

    public RealTimePresenterImpl(IRealTimeView view, IApiService apiService) {

        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void onCreateBnPressed() {
        view.showCreateDialog();
        GetScenicListRequest request = new GetScenicListRequest();
        Call<GetScenicListResult> scenicListCall = apiService.getScenicList(request);
        scenicListCall.enqueue(new Callback<GetScenicListResult>() {
            @Override
            public void onResponse(Call<GetScenicListResult> call, Response<GetScenicListResult> response) {
                GetScenicListResult result = response.body();
                if (result != null) {
                    scenicModels = result.getScenicModels();
                    List<String> l = new ArrayList<>();
                    for (ScenicModel s : scenicModels) {
                        l.add(s.getName());
                    }
                    view.updateScenicList(l);
                }
            }

            @Override
            public void onFailure(Call<GetScenicListResult> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onCreateSure() {
        String scenicName = view.getScenicName();
        if (Checker.isEmpty(scenicName)) {
            view.showToase("未设置团队名");
            return;
        }
        final int which = view.getScenicPosition();
        if (which == -1) {
            view.showToase("未选择景点");
            return;
        }
        final String scenicRange = view.getScenicRange();
        if (Checker.isEmpty(scenicRange)) {
            view.showToase("未设置景点范围");
            return;
        }
        CreateTeamRequest request = new CreateTeamRequest();
        request.setGuideUserId(User.getInstance().getUserId());
        request.setName(scenicName);
        request.setScenicId(scenicModels.get(which).getScenicId());
        request.setScenicRange(Integer.parseInt(scenicRange));
        Call<CreateTeamResult> teamCall = apiService.createTeam(request);
        teamCall.enqueue(new Callback<CreateTeamResult>() {
            @Override
            public void onResponse(Call<CreateTeamResult> call, Response<CreateTeamResult> response) {
                view.cancelWaitingTip();
                CreateTeamResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {
                    view.showToase("创建成功");
                    scenicModel = scenicModels.get(which);
                    view.animateToLocation(Float.parseFloat(scenicRange), scenicModel.getLongitude(), scenicModel.getLatitude());
                }
            }

            @Override
            public void onFailure(Call<CreateTeamResult> call, Throwable t) {
                t.printStackTrace();
                view.cancelWaitingTip();
                if (t instanceof SocketTimeoutException) {
                    view.showErrorMsgDialog("连接超时");
                } else if (t instanceof SocketException && t.getMessage().equals("Socket closed")) {

                } else {
                    view.showErrorMsgDialog("操作失败");
                }
            }
        });
        view.showWaitingTip();
    }

    @Override
    public void onDeleteBnPressed() {
        view.showDeleteDialog();
        cmdFlag = 1;
        GetTeamListRequest request = new GetTeamListRequest();
        request.setListType(GetTeamListRequest.LIST_TYPE.DELETE_LIST);
        request.setGuideUserId(User.getInstance().getUserId());
        Call<GetTeamListResult> teamListCall = apiService.getTeamList(request);
        teamListCall.enqueue(new Callback<GetTeamListResult>() {
            @Override
            public void onResponse(Call<GetTeamListResult> call, Response<GetTeamListResult> response) {
                GetTeamListResult result = response.body();
                if (result != null) {
                    if (result.getCode() != 0) {
                        view.showToase(result.getMessage());
                    } else {
                        travelTeamModels = result.getTravelTeamModels();
                        view.updateTeamList(travelTeamModels);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTeamListResult> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onJoinBnPressed() {
        view.showJoinDialog();
        cmdFlag = 2;
        GetTeamListRequest request = new GetTeamListRequest();
        request.setListType(GetTeamListRequest.LIST_TYPE.JOIN_LIST);
        request.setPassengerUserId(User.getInstance().getUserId());
        Call<GetTeamListResult> teamListCall = apiService.getTeamList(request);
        teamListCall.enqueue(new Callback<GetTeamListResult>() {
            @Override
            public void onResponse(Call<GetTeamListResult> call, Response<GetTeamListResult> response) {
                GetTeamListResult result = response.body();
                if (result != null) {
                    if (result.getCode() != 0) {
                        view.showToase(result.getMessage());
                    } else {
                        travelTeamModels = result.getTravelTeamModels();
                        view.updateTeamList(travelTeamModels);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTeamListResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onOutBnPressed() {
        view.showOutDialog();
        cmdFlag = 3;
        GetTeamListRequest request = new GetTeamListRequest();
        request.setListType(GetTeamListRequest.LIST_TYPE.OUT_LIST);
        request.setPassengerUserId(User.getInstance().getUserId());
        Call<GetTeamListResult> teamListCall = apiService.getTeamList(request);
        teamListCall.enqueue(new Callback<GetTeamListResult>() {
            @Override
            public void onResponse(Call<GetTeamListResult> call, Response<GetTeamListResult> response) {
                GetTeamListResult result = response.body();
                if (result != null) {
                    if (result.getCode() != 0) {
                        view.showToase(result.getMessage());
                    } else {
                        travelTeamModels = result.getTravelTeamModels();
                        view.updateTeamList(travelTeamModels);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTeamListResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onTeamItemClick(final int position) {
        if (travelTeamModels == null || travelTeamModels.isEmpty()) {
            return;
        }
        switch (cmdFlag) {
            case 1:
                deleteTeam(position);
                break;
            case 2:
                joinTeam(position);
                break;
            case 3:
                outTeam(position);
                break;
        }
        view.showWaitingTip();
    }

    @Override
    public void onStartBnPressed() {
        GuideCmdRequest request = new GuideCmdRequest();
        request.setGuideUserId(User.getInstance().getUserId());
        request.setCmdType(GuideCmdRequest.CMD_TYPE.START_TOUR);
        Call<GuideCmdResult> guideCmdResultCall = apiService.guideCmd(request);
        guideCmdResultCall.enqueue(new Callback<GuideCmdResult>() {
            @Override
            public void onResponse(Call<GuideCmdResult> call, Response<GuideCmdResult> response) {
                view.cancelWaitingTip();
                GuideCmdResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {
                    view.showToase("开始导游");
                }
            }

            @Override
            public void onFailure(Call<GuideCmdResult> call, Throwable t) {
                t.printStackTrace();
                view.cancelWaitingTip();
                if (t instanceof SocketTimeoutException) {
                    view.showErrorMsgDialog("连接超时");
                } else if (t instanceof SocketException && t.getMessage().equals("Socket closed")) {

                } else {
                    view.showErrorMsgDialog("操作失败");
                }
            }
        });
        view.showWaitingTip();
    }

    @Override
    public void onStopBnPressed() {
        GuideCmdRequest request = new GuideCmdRequest();
        request.setGuideUserId(User.getInstance().getUserId());
        request.setCmdType(GuideCmdRequest.CMD_TYPE.STOP_TOUR);
        Call<GuideCmdResult> guideCmdResultCall = apiService.guideCmd(request);
        guideCmdResultCall.enqueue(new Callback<GuideCmdResult>() {
            @Override
            public void onResponse(Call<GuideCmdResult> call, Response<GuideCmdResult> response) {
                view.cancelWaitingTip();
                GuideCmdResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {
                    view.showToase("结束导游");
                }
            }

            @Override
            public void onFailure(Call<GuideCmdResult> call, Throwable t) {
                t.printStackTrace();
                view.cancelWaitingTip();
                if (t instanceof SocketTimeoutException) {
                    view.showErrorMsgDialog("连接超时");
                } else if (t instanceof SocketException && t.getMessage().equals("Socket closed")) {

                } else {
                    view.showErrorMsgDialog("操作失败");
                }
            }
        });
        view.showWaitingTip();
    }

    private void outTeam(final int position) {
        OutTeamRequest request = new OutTeamRequest();
        request.setTeamId(travelTeamModels.get(position).getTeamId());
        request.setUserId(User.getInstance().getUserId());
        Call<OutTeamResult> outTeamResultCall = apiService.outTeam(request);
        outTeamResultCall.enqueue(new Callback<OutTeamResult>() {
            @Override
            public void onResponse(Call<OutTeamResult> call, Response<OutTeamResult> response) {
                view.cancelWaitingTip();
                OutTeamResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {
                    travelTeamModels.remove(position);
                    view.updateTeamList(travelTeamModels);
                    view.showToase("退出成功");
                }
            }

            @Override
            public void onFailure(Call<OutTeamResult> call, Throwable t) {
                t.printStackTrace();
                view.cancelWaitingTip();
                if (t instanceof SocketTimeoutException) {
                    view.showErrorMsgDialog("连接超时");
                } else if (t instanceof SocketException && t.getMessage().equals("Socket closed")) {

                } else {
                    view.showErrorMsgDialog("操作失败");
                }
            }
        });
    }

    private void joinTeam(final int position) {
        JoinTeamRequest request = new JoinTeamRequest();
        request.setTeamId(travelTeamModels.get(position).getTeamId());
        request.setUserId(User.getInstance().getUserId());
        Call<JoinTeamResult> joinTeamResultCall = apiService.joinTeam(request);
        joinTeamResultCall.enqueue(new Callback<JoinTeamResult>() {
            @Override
            public void onResponse(Call<JoinTeamResult> call, Response<JoinTeamResult> response) {
                view.cancelWaitingTip();
                JoinTeamResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {
                    travelTeamModels.remove(position);
                    view.updateTeamList(travelTeamModels);
                    view.showToase("加入成功");
                }
            }

            @Override
            public void onFailure(Call<JoinTeamResult> call, Throwable t) {
                t.printStackTrace();
                view.cancelWaitingTip();
                if (t instanceof SocketTimeoutException) {
                    view.showErrorMsgDialog("连接超时");
                } else if (t instanceof SocketException && t.getMessage().equals("Socket closed")) {

                } else {
                    view.showErrorMsgDialog("操作失败");
                }
            }
        });
    }

    private void deleteTeam(final int position) {
        DeleteTeamRequest request = new DeleteTeamRequest();
        request.setTeamId(travelTeamModels.get(position).getTeamId());
        Call<DeleteTeamResult> deleteTeamResultCall = apiService.deleteTeam(request);
        deleteTeamResultCall.enqueue(new Callback<DeleteTeamResult>() {
            @Override
            public void onResponse(Call<DeleteTeamResult> call, Response<DeleteTeamResult> response) {
                view.cancelWaitingTip();
                DeleteTeamResult result = response.body();
                if (result == null) {
                    view.showErrorMsgDialog("连接失败");
                } else if (result.getCode() != 0) {
                    view.showErrorMsgDialog(result.getMessage());
                } else {
                    travelTeamModels.remove(position);
                    view.updateTeamList(travelTeamModels);
                    view.showToase("删除成功");
                }

            }

            @Override
            public void onFailure(Call<DeleteTeamResult> call, Throwable t) {
                t.printStackTrace();
                view.cancelWaitingTip();
                if (t instanceof SocketTimeoutException) {
                    view.showErrorMsgDialog("连接超时");
                } else if (t instanceof SocketException && t.getMessage().equals("Socket closed")) {

                } else {
                    view.showErrorMsgDialog("操作失败");
                }
            }
        });
    }

}
