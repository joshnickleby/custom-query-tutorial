package com.astontech.dataaccess

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class DataAccessApplication

fun main(args: Array<String>) {
	runApplication<DataAccessApplication>(*args)
}
