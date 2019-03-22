package com.slogup.sgsgerpstock.scene.signin

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class SignInInteractorUnitTest {

    @Test
    fun fetchSignInData_with_validInput_shouldCall_presentSignInData() {
        // Given
        val interactor = SignInInteractor()
        val presenterSpy = SignInPresenterSpy()
        interactor.presenter = presenterSpy

        // When
        SignIn.SignInData.Request().apply {
            // set request mock data...
            interactor.fetchSessionData(this)
        }

        // Then
        Assert.assertTrue(
            "When the valid input is passed to SignInInteractor "
                    + "Then presentSignInData should be called",
            presenterSpy.presentSignInDataIsCalled
        )
    }

    private inner class SignInPresenterSpy : SignInPresentationLogic {

        internal var presentSignInDataIsCalled = false
        internal lateinit var responseCopy: SignIn.SignInData.Response

        override fun presentSignInData(response: SignIn.SignInData.Response) {
            presentSignInDataIsCalled = true
            responseCopy = response
        }
    }
}