package com.slogup.sgsgerpstock.scene.signin

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference


@RunWith(RobolectricTestRunner::class)
class SignInPresenterUnitTest {

    @Test
    fun presentSignInData_with_validInput_shouldCall_displaySignInData() {
        // Given
        val presenter = SignInPresenter()
        val activitySpy = SignInActivitySpy()
        presenter.activity = WeakReference(activitySpy)

        // When
        SignIn.SignInData.Response().apply {
            // set response mock data...
            presenter.presentSignInData(this)
        }

        // Then
        Assert.assertTrue(
            "When the valid input is passed to SignInPresenter "
                    + "Then displaySignInData should be called",
            activitySpy.displaySignInDataIsCalled
        )
    }

    private inner class SignInActivitySpy : SignInDisplayLogic {

        internal var displaySignInDataIsCalled = false
        internal lateinit var viewModelCopy: SignIn.SignInData.ViewModel

        override fun displaySignInData(viewModel: SignIn.SignInData.ViewModel) {
            displaySignInDataIsCalled = true
            viewModelCopy = viewModel
        }
    }
}