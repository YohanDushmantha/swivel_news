package com.swivel.models.base

enum class DataSource {
    REMOTE,
    LOCAL,
    SHARED_PREF,
    FIREBASE_DB,
    S3_BUCKET,
    ANY
}