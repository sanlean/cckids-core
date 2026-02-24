package io.github.sanlean.cckids.database

import io.github.sanlean.cckids.Database
import io.github.sanlean.cckids.database.Aula
import io.github.sanlean.cckids.database.CheckIn
import io.github.sanlean.cckids.database.Material
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseFactory(
    private val driverFactory: DriverFactory
) {
    private val scope = CoroutineScope(Dispatchers.Default)

    fun loadDatabase(loaded: (Database) -> Unit){
        val launch: suspend CoroutineScope.() -> Unit = {
            val database = Database(
                driver = driverFactory.createDriver(),
                AulaAdapter = Aula.Adapter(
                    dateTimeAdapter = localDateTimeAdapter,
                    criadoEmAdapter = localDateTimeAdapter,
                    atualizadoEmAdapter = localDateTimeAdapter
                ),
                CheckInAdapter = CheckIn.Adapter(
                    dataHoraAdapter = localDateTimeAdapter
                ),
                MaterialAdapter = Material.Adapter(
                    dataHoraAdapter = localDateTimeAdapter
                )
            )
            loaded.invoke(database)
        }
        scope.launch(block = launch)
    }
}