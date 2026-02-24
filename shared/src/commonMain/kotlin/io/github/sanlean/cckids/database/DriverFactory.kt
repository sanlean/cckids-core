package io.github.sanlean.cckids.database

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
  suspend fun createDriver(): SqlDriver
}