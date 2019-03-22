package com.slogup.sgsgerpstock.scene.signin

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class SignInActivityUnitTest {

    @Test
    fun onCreate_shouldCall_fetchSignInData() {
        // Given
        val interactorSpy = SignInInteractorSpy()

        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchData to test our condition
        val activity = SignInActivity()
        activity.interactor = interactorSpy

        // When
        activity.fetchSignInData()

        // Then
        Assert.assertTrue(interactorSpy.fetchSignInDataIsCalled)
    }

    @Test
    fun onCreate_Calls_fetchSignInData_withCorrectData() {
        // Given
        val interactorSpy = SignInInteractorSpy()
        val activity = SignInActivity()
        activity.interactor = interactorSpy

        // When
        activity.fetchSignInData()

        // Then
        // Assert.assertNotNull(interactorSpy.requestCopy.context)
    }

    private inner class SignInInteractorSpy : SignInBusinessLogic {

        internal var fetchSignInDataIsCalled = false
        internal lateinit var requestCopy: SignIn.SignInRequest

        override fun fetchSessionData(request: SignIn.SignInRequest) {
            fetchSignInDataIsCalled = true
            requestCopy = request
        }
    }
}