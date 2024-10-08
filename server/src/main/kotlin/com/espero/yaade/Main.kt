package com.espero.yaade

import com.espero.yaade.init.createDaoManager
import com.espero.yaade.server.Server
import com.espero.yaade.server.utils.configureDatabindCodec
import io.vertx.core.Vertx

val PORT = System.getenv("YAADE_PORT")?.toInt() ?: 9339
val JDBC_URL = System.getenv("YAADE_JDBC_URL") ?: "jdbc:h2:file:./app/data/yaade-db"
val JDBC_USR = System.getenv("YAADE_JDBC_USERNAME") ?: "sa"
val JDBC_PWD = System.getenv("YAADE_JDBC_PASSWORD") ?: ""
val ADMIN_USERNAME: String = System.getenv("YAADE_ADMIN_USERNAME") ?: ""
val BASE_PATH: String = System.getenv("YAADE_BASE_PATH") ?: ""
val FILE_STORAGE_PATH: String = System.getenv("YAADE_FILE_STORAGE_PATH") ?: "./app/data/files"

fun main() {
    configureDatabindCodec()
    val daoManager = createDaoManager(JDBC_URL, JDBC_USR, JDBC_PWD)
    val vertx = Vertx.vertx()
    vertx.deployVerticle(Server(PORT, daoManager))
}
