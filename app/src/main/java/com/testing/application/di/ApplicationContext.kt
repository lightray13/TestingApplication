package com.testing.application.di

import javax.inject.Qualifier

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ApplicationContext()
