fun main(argv: Array<String>){

	var chapulin: ChapulinColorado = ChapulinColorado.yAhoraQuienPodraAyudarnos()
	chapulin.noContabanConMiAstucia()
	chapulin.quienSoy()
	
	
	var otraPersona = Persona("Pepe")
	otraPersona.quienSoy()
	

}

open class Persona{

	constructor(nombre: String){
		this.nombre = nombre
	}

	protected var nombre: String
	
	public fun quienSoy(){
		println(nombre)
	}

}

class ChapulinColorado : Persona{
	
	private constructor(nombre: String) : super(""){
		this.nombre = "Chapulin colorado"
	}

	companion object{
		private var instance: ChapulinColorado = ChapulinColorado("Chapulin Colorado")
		public fun yAhoraQuienPodraAyudarnos(): ChapulinColorado{
			return ChapulinColorado.instance;
		}
	}

	public fun resolverSituacion(){
		println("No contaban con mi astucia")
	}

}


