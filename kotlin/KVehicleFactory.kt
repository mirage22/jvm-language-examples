/*
 * Copyright (c) 2022, Miro Wengner (@miragemiko)
 *
 * jvm-languages-examples is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jvm-languages-examples is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 *  along with jvm-languages-examples. If not, see <http://www.gnu.org/licenses/>.
 */

class KVehicleFactory {
    companion object {
        fun createVehicle(type: String): KVehicle = when(type){
            "CAR" -> KCar()
            "BUS" -> KBus()
            "TRUCK" -> KTruck()
            else -> throw IllegalArgumentException("illegal type:$type")
        }
    }

    sealed interface KVehicle {}
    class KCar : KVehicle {}
    class KTruck : KVehicle {}
    class KBus : KVehicle {}
}

fun main(){
    println("Kotlin, vehicle factory example")
    val vehicle = KVehicleFactory.createVehicle("BUS")
    println("Vehicle Type: $vehicle")
}
