package com.bettermile.tourmanager.controllers

import com.bettermile.tourmanager.model.Driver
import com.bettermile.tourmanager.model.Tour
import com.bettermile.tourmanager.services.TourService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tour")
class TourController(private val tourService: TourService) {

    @GetMapping("/list")
    fun getTours(): List<Tour> = tourService.retrieveAllTours()

    @GetMapping("/update-tour-date/{id}/{date}")
    fun modifyTourDescription(@PathVariable id: Long, @PathVariable date: String): String {
        return try {
            val tour = tourService.retrieveAllTours().find { it.id == id }
            if (tour != null) {
                tour.date = date
                tourService.saveTour(tour)
                "Description updated."
            } else {
                "Tour not found."
            }
        } catch (e: Exception) {
            "Failed to update tour."
        }
    }


    @GetMapping("/add-driver/{driverName}")
    fun registerDriver(@PathVariable driverName: String): String {
        val driver = Driver(id = (Math.random() * 1000).toLong(), name = driverName)
        tourService.saveDriver(driver)
        return "Driver $driverName registered."
    }

    @GetMapping("/assign-driver/{tourId}/{driverId}")
    fun linkDriver(@PathVariable tourId: Long, @PathVariable driverId: Long): String {
        return tourService.linkDriverToTour(tourId, driverId)
    }

    @PostMapping("/new")
    fun createTour(@RequestBody tour: Tour) {
        tourService.saveTour(tour)
    }

    @PostMapping("/remove-tour/{id}")
    fun removeTour(@PathVariable id: Long): String {
        return try {
            tourService.deleteTour(id)
            "Tour removed."
        } catch (e: Exception) {
            "Failed to remove tour."
        }
    }
}
