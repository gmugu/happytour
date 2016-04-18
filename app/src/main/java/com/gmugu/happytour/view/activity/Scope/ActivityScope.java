package com.gmugu.happytour.view.activity.Scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by mugu on 16-4-2 下午4:44.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}
