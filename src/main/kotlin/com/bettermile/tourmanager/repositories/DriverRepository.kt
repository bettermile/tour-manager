package com.bettermile.tourmanager.repositories

import com.bettermile.tourmanager.model.Driver
import org.springframework.data.repository.CrudRepository

interface DriverRepository : CrudRepository<Driver, Long>