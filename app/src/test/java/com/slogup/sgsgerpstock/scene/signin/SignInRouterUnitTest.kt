package com.slogup.sgsgerpstock.scene.signin

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference


@RunWith(RobolectricTestRunner::class)
class SignInRouterUnitTest {

    @Test
    fun test_SignInRouter_navigateToNextScene_when_Input_Is() {
        // Given
        // Setup Data

        val router = SignInRouter()
        val activity = SignInActivity()
        activity.router = router
        router.activity = WeakReference(activity)

        // When
        val nextScene = router.navigateToMain()

        // Then
//        Assert.assertEquals(
//            "When the some data passed to SignInRouter" +
//                    " Then next scene should be ...",
//            nextScene, Intent()
//        )
    }
}