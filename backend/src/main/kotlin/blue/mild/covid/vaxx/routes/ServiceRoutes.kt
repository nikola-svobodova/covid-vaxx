package blue.mild.covid.vaxx.routes

import blue.mild.covid.vaxx.dao.DatabaseSetup
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.kodein.di.instance
import org.kodein.di.ktor.di

/**
 * Registers prometheus data.
 */
fun Routing.serviceRoutes() {
    val version by di().instance<String>("version")

    /**
     * Send data about version.
     */
    get(Routes.version) {
        call.respond(VersionDtoOut(version))
    }

    /**
     * Responds only 200 for ingres.
     */
    get(Routes.status) {
        call.respond(HttpStatusCode.OK)
    }

    /**
     * More complex API for indication of all resources.
     */
    get(Routes.statusHealth) {
        if (DatabaseSetup.isConnected()) {
            call.respond(HealthDtoOut("healthy"))
        } else {
            call.respond(HttpStatusCode.ServiceUnavailable, HealthDtoOut("DB connection is not working"))
        }
    }
}

data class VersionDtoOut(val version: String)
data class HealthDtoOut(val health: String)
