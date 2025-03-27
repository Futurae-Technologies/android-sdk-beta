package com.futurae.futuraedemo.util

import com.futurae.sdk.FuturaeSDK
import com.futurae.sdk.public_api.session.model.SessionInfoQuery

suspend fun getSessionInfo(
    query: SessionInfoQuery,
    isSessionInfoWithoutUnlockEnabled: Boolean
) = if (isSessionInfoWithoutUnlockEnabled) {
        FuturaeSDK.client.sessionApi.getSessionInfoWithoutUnlock(query).await()
    } else {
        FuturaeSDK.client.sessionApi.getSessionInfo(query).await()
    }