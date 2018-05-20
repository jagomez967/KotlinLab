fun main(args: Array<String>){

	var miComputadoraNueva: Computadora = Computadora.ArmadorDeComputadora()
		.agregarCpu(3, 2)
		.agregarRam(15, 2)
		.agregarStorage(512, true)
		.entregarPc()

	miComputadoraNueva.mostrarPartes()
	var precio = miComputadoraNueva.calcularPrecioTotal()
	println("Total ---------- " + precio)

}


abstract class Componente{

	private var ultimoComponente: Componente? = null

	
	
	fun agregarComponente(componente: Componente){
		if(ultimoComponente != null){
			ultimoComponente?.agregarComponente(componente)
		}else{
			ultimoComponente = componente
		}
		
	}
	
	fun calcularPrecioTotal(): Int{
		var total: Int = getPrice()
		var anterior = ultimoComponente?.calcularPrecioTotal()
		if(anterior != null){
			total += anterior
		}
		return total
	}
	
	abstract fun getPrice(): Int
	
	fun mostrarPartes(){
		println(getNombre())
		ultimoComponente?.mostrarPartes()
	}
	
	abstract fun getNombre(): String
	
}


class CPU : Componente{

	private var cores: Int
	private var frecuencia: Int
	
	public constructor(cores: Int, frecuencia: Int){
		this.cores = cores
		this.frecuencia = frecuencia
	}
	
	override fun getPrice(): Int{
		return 1000 *  cores * frecuencia
	}
	
	override fun getNombre(): String{
		return "CPU con " + cores + " cores de " + frecuencia + " MHz"
	}
}

class Ram : Componente{

	private var capacity: Int
	private var frecuencia: Int
	
	public constructor(capacity: Int, frecuencia: Int){
		this.capacity = capacity
		this.frecuencia = frecuencia
	}
	
	override fun getPrice(): Int{
		return 100 *  capacity * frecuencia
	}
	
	override fun getNombre(): String{
		return "Memoria RAM de " + capacity + " GB de " + frecuencia + " MHz"
	}
}

class Storage : Componente{

	private var capacity: Int
	private var estadoSolido: Boolean
	
	public constructor(capacity: Int, estadoSolido: Boolean){
		this.capacity = capacity
		this.estadoSolido = estadoSolido
	}
	
	override fun getPrice(): Int{
		return (if (estadoSolido) 50 else 10) *  capacity 
	}
	
	override fun getNombre(): String{
		var nombre = "Storage "
		nombre += if(estadoSolido) "SSD" else  "HD"
		return nombre + " de " + capacity + " GB"
	}
}







class Computadora{

	private constructor(ultimoComponente: Componente){
		this.ultimoComponente = ultimoComponente
	}

	private var ultimoComponente: Componente

	
	public fun calcularPrecioTotal(): Int{
		return ultimoComponente.calcularPrecioTotal()
	}
	
	public fun mostrarPartes(){
		ultimoComponente.mostrarPartes()
	}
	
	public class ArmadorDeComputadora{
	
		private var computadora: Computadora? = null
		private var componenteActual: Componente
		
		public constructor(){
			componenteActual = CPU(1, 1)
			componenteActual.agregarComponente(Ram(1, 1))
			componenteActual.agregarComponente(Storage(128, false))
		}
		
		public fun agregarCpu(cores: Int, frecuencia: Int) : ArmadorDeComputadora{
			componenteActual.agregarComponente(CPU(cores, frecuencia))
			return this
		}
	
		public fun agregarRam(capacity: Int, frecuencia: Int) : ArmadorDeComputadora{
			componenteActual.agregarComponente(Ram(capacity, frecuencia))
			return this
		}
	
		public fun agregarStorage(capacity: Int, estadoSolido: Boolean) : ArmadorDeComputadora{
			componenteActual.agregarComponente(Storage(capacity, estadoSolido))
			return this
		}
		
		public fun entregarPc(): Computadora{
			return Computadora(componenteActual)
		}
		
	}
	
}



