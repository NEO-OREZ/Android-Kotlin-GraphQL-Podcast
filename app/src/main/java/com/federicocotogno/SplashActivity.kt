package com.federicocotogno

import android.app.Activity
import com.federicocotogno.JsonRetroSpotify.MainActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE


class SplashActivity {
    var CLIENT_ID = "525361da76bd4b53bea5588dd86d931c"
    var REDIRECT_URI = "com.federicocotogno.JsonRetroSpotify://callback"
    var SCOPES = "user-read-recently-played, user-library-modify, user-read-email,user-read-private"
    var REQCODE = 1337


    private fun authenticateSpotify() {
        var builderv = AuthenticationRequest.Builder(CLIENT_ID,AuthenticationResponse.Type.TOKEN,REDIRECT_URI)

        builderv.setScopes(arrayOf(SCOPES))

        val requestv = builderv.build()
        AuthenticationClient.openLoginActivity(MainActivity(), REQCODE, requestv)
    }

}