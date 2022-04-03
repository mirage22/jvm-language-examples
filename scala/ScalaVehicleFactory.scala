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

sealed trait SVehicle {}
private class SCar extends SVehicle {}
private class STruck extends SVehicle {} 
private class SBus extends SVehicle {}


object SVehicleFactory {
    def main(args: Array[String]) = {
        println("Scala, vehicle factory example")
        var vehicle = createVehicle("TRUCK")
        println(printf("Vehicle Type: %s", vehicle)) 
    }

    def createVehicle(vehicleType: String): SVehicle = vehicleType match {
            case "CAR" => new SCar
            case "TRUCK" => new STruck
            case "BUS" => new SBus 
            case _ => throw new IllegalArgumentException("illegal type: " + vehicleType)
        } 
}