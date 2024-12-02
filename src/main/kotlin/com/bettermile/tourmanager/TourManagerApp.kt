package com.bettermile.tourmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["com.bettermile.tourmanager"])
class TourManagerApp

fun main(args: Array<String>) {
    runApplication<TourManagerApp>(*args)
}