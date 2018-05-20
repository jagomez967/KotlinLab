fun main(argv: Array<String>){

	println("Todo es muy bonito")

}


interface Belico{

	fun getRadioDeFuego(): Int
	fun abrirFuegoA(lat: Float, lng: Float)

}


open class Vehiculo{

	constructor(){
		peso = 32
	}

	var peso: Int = 0
	var largo: Int = 0
	var ancho: Int = 0
	var alto: Int = 0
	var maximaCarga: Int = 0
	open fun irA(lat: Float, lng: Float){
		// Juntar a un monton de monos que empujen
		println("Monos empujando a " +lat+ ", " + lng)
	}
	var velocidadMaxima: Int = 0
}

open class VehiculoTerrestre : Vehiculo{
	
	constructor() : super(){
		largo = 13
	}
	
}

open class VehiculoMarino : Vehiculo{

	constructor() : super(){
	
	}

	var factorDeFlotabilidad: Int = 0
	var desplazamientoDeAgua: Int = 0
	var calado: Int = 0
	
	fun soltarAmarras(){}
	
	fun echarElAncla(){}
	
}

abstract class VehiculoAereo: Vehiculo{

	constructor() : super()

	var altitudMaxima: Int = 0
	var velocidadDePlaneo: Int = 0
	
	override fun irA(lat: Float, lng: Float){
		despegar()
		// movilizarce
		aterrizar()
	}	
	
	abstract fun despegar()
	
	abstract fun aterrizar()
	
}

class F22Raptor : VehiculoAereo, Belico{

	constructor() : super()
	
	override fun getRadioDeFuego(): Int{
		return 23
	}
	
	override fun abrirFuegoA(lat: Float, lng: Float){
		println("Boooooooom! en " + lat + ", " + lng)
	}

	override fun despegar(){
		println("Carreteo 400 metros y arriba")
		
	}
	
	override fun aterrizar(){
		println("Aterrizo y en 200m de pista pare")
	}
	
}


abstract class Hercules : VehiculoAereo{

	constructor() : super()

	
}

class HerculesAbastecimiento : Hercules{
	
	override fun despegar(){
		println("Carreteo 3000 metros y arriba")
		
	}
	
	override fun aterrizar(){
		if(peso < 100)
			println("Aterrizo y en 200m de pista pare")
		else{
			println("Aterrizo y en 800m de pista pare")
		}
	}
}

