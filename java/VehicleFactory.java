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
 *  along with jvm-languages-exampless. If not, see <http://www.gnu.org/licenses/>.
 */

public class VehicleFactory {
    private sealed interface Vehicle {}
    private static final class Car implements Vehicle {}
    private static final class Bus implements Vehicle {}
    private static final class Truck implements Vehicle {}

    public static void main(String[] args) {
       System.out.println("Java 18, vehicle factory example...");
       var vehile = createVehicle("TRUCK");
       System.out.println("""
               Vehicle Type: '%s' """.formatted(vehile));
    }

    private static Vehicle createVehicle(String type) {
        return switch(type){
            case "CAR" -> new Car();
            case "BUS" -> new Bus();
            case "TRUCK" -> new Truck();
            default -> throw new IllegalArgumentException("""
                illegal type: %s """.formatted(type));
        };
    }
}