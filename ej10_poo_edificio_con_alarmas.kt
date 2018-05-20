
fun main(args: Array<String>) {

	var constructora = ConstructoraDeEdificios("Av. Santa Fe 1234, CABA")
	constructora.construirNuevoPiso(0)
	constructora.construirNuevoPiso(8)
	constructora.construirNuevoPiso(8)
	constructora.construirNuevoPiso(8)
	constructora.construirNuevoPiso(8)
	constructora.construirNuevoPiso(8)
	constructora.construirNuevoPiso(8)
	constructora.construirNuevoPiso(6)
	constructora.construirNuevoPiso(6)
	constructora.construirNuevoPiso(6)
	constructora.construirNuevoPiso(6)
	constructora.construirNuevoPiso(6)
	constructora.construirNuevoPiso(6)
	constructora.construirNuevoPiso(6)
	constructora.construirNuevoPiso(3)
	constructora.construirNuevoPiso(3)
	constructora.construirNuevoPiso(2)
	constructora.construirNuevoPiso(2)
	constructora.construirNuevoPiso(1)
	constructora.agregarAsensores(2)
	
	var edificio = constructora.inagurar()
	if(edificio == null){
		println("Demanda a la constructora")
		return
	}
	
	var pizza = Pizza()
	var bomba = Bomba()
	var pizzaMal = Pizza()
	pizzaMal.quesoVolcado = true
	
	chePibeEntregaEn(edificio, 1, 2, pizza)
	chePibeEntregaEn(edificio, 1,4, bomba)
	chePibeEntregaEn(edificio, 8,3, pizza)
	chePibeEntregaEn(edificio, 2,6, bomba)
	chePibeEntregaEn(edificio, 18, 0, pizza)
	chePibeEntregaEn(edificio, 2,1, pizzaMal)
	
	
	
	

	

}

fun abs(v: Int) : Int{
	if(v < 0) return v * -1
	return v
}

class ConstructoraDeEdificios{

	var edificioEnObra: Edificio

	constructor(direccion : String){
		edificioEnObra = Edificio(direccion)
		
	}
	
	fun construirNuevoPiso(cantDepartamentos: Int){
		var nuevoPiso = Piso(edificioEnObra.pisos.size, edificioEnObra)
		for(  nroDepartamento in 0..(cantDepartamentos - 1)){
			var nuevoDetector = DetectorDeHumo(edificioEnObra.centralSeguridad)
			var nuevaAlarma = Alarma()
			var nuevoDepto = Departamento(nroDepartamento, nuevoPiso, nuevoDetector, nuevaAlarma)
			edificioEnObra.centralSeguridad.conectarAlarma(nuevaAlarma)
			nuevoPiso.departamentos.add(nuevoDepto)
		}
		edificioEnObra.pisos.add(nuevoPiso)

	}
	
	fun agregarAsensores(cantidad: Int){
		if(edificioEnObra.pisos.size == 0){
			return
		}
		for( nroAsensor in 0..(cantidad - 1) ){
			var nuevoAsensor = Asensor(nroAsensor, edificioEnObra)
			edificioEnObra.asensores.add(nuevoAsensor)
		}
	}
	
	fun inagurar() : Edificio?{
		if(edificioEnObra.pisos.size <= 1){
			println("Me faltan pisos")
			return null
		}
		
		if(edificioEnObra.asensores.size == 0){
			println("me faltan asensores")
			return null
		}
	
		return edificioEnObra
	}

	
	
	

}



fun chePibeEntregaEn(edificio: Edificio, piso: Int, departamento: Int, p: IPaquete){
	println("\n\n==================================\nMandame una pizza a:\n" + edificio.direccion + ", Piso " + piso + ", Depto " + departamento+ "\n")
	var plantaBaja: Piso = edificio.getPlantaBaja()
	println("Entro al piso " + plantaBaja.numero)
	var asensor = plantaBaja.llamarAsensor()
	println("Llego el asensor " + asensor.numero)
	var pisoN = asensor.irAlPiso(piso)
	println("Subi al piso " + pisoN.numero)
	var depto = pisoN.dameDepartamento(departamento)
	depto.tocarTimbre()
	depto.recibirPaquete(p)

}

interface IPaquete{
	fun abrir();
}

class Bomba : IPaquete{
	override fun abrir(){
		println("BOOOOOOOOOOOOOOM")
		throw EventoQueHumea("Exploto una bomba")
	}
}


class EventoQueHumea(message: String) : Exception(message){}

class SeVolcoElQuesoEnLaEsquinaDeLaCaja : Exception{

	public constructor(message: String) : super(message){}

}


class Pizza : IPaquete{
	override fun abrir(){
		if(quesoVolcado) {
			throw SeVolcoElQuesoEnLaEsquinaDeLaCaja("Indignacion")
		}
		println("Que rico")
	}
	
	public var quesoVolcado : Boolean = false
}

class Departamento{

	private var detector: DetectorDeHumo
	private var alarma: Alarma

	constructor(numero: Int, piso: Piso, detector: DetectorDeHumo, alarma : Alarma){
		this.numero = numero
		this.piso = piso
		this.detector = detector
		this.alarma = alarma
	}
	
	var numero: Int
	var piso: Piso
	
	fun tocarTimbre(){
		println("Riiiing en el Piso " + piso.numero + ", Puerta " + numero)
	}
	
	fun recibirPaquete(p : IPaquete){

		try{
			p.abrir()
		}catch( e : EventoQueHumea){
			println(e.message)
			detector.darAviso()
		}catch( e : SeVolcoElQuesoEnLaEsquinaDeLaCaja){
			println("Para que le deje propina")
		}catch( e : Exception){
			println(e.message)

		}
		
	}

}


class DetectorDeHumo{
	private var central : CentralDeSeguridad

	constructor(central : CentralDeSeguridad){
		this.central = central
	}

	fun darAviso(){
		this.central.dispararAlarmas()
	}
}

class Alarma{
	fun sonar(){
		println("FUEGO!!! Salvese quien pueda!!!!")
	}
	
	fun callar(){
		
	}
}

class CentralDeSeguridad{

	private var detectoresDeHumo : MutableList<DetectorDeHumo> = mutableListOf()
	private var alarmas : MutableList<Alarma> = mutableListOf()

	public fun conectarAlarma(a : Alarma){
		alarmas.add(a)
	}
	
	//public fun conectarDetectorDeHumo(d : DetectorDeHumo){
	//	detectoresDeHumo.add(d)
	//}
	
	public fun dispararAlarmas(){
		for(alarma in alarmas){
			alarma.sonar()
		}
	}
	
	public fun detenerAlarmas(){
		for(alarma in alarmas){
			alarma.callar()
		}
	}
	
}





class Asensor{

	constructor(numero: Int, edificio: Edificio){
		this.numero = numero
		this.edificio = edificio
		this.pisoActual =  edificio.getPlantaBaja()
	}

	var numero: Int
	var pisoActual: Piso
	var edificio: Edificio

	fun irAlPiso(nroPiso: Int): Piso{
		edificio.mandarAsensorAlPiso(this, nroPiso)
		return pisoActual
	}
	
}

class Piso{

	constructor(numero: Int, edificio: Edificio){
		this.numero = numero
		this.edificio = edificio
		this.departamentos = mutableListOf()
	} 

	var departamentos: MutableList<Departamento>
	var numero: Int
	var edificio: Edificio
	
	fun llamarAsensor(): Asensor{
		return edificio.llamarAsensorAlPiso(this.numero)
	}
	
	fun dameDepartamento(timbre: Int): Departamento{
		return departamentos.get(timbre)
	}

}

class Edificio {


	constructor(direccion: String){
		this.pisos = mutableListOf()
		this.asensores = mutableListOf()
		this.direccion = direccion
		this.centralSeguridad = CentralDeSeguridad()
	}

	
	var centralSeguridad : CentralDeSeguridad
	var pisos: MutableList<Piso>
	var asensores: MutableList<Asensor>
	var direccion: String
	
	fun imprimirEstructura(){
		for( piso in pisos){
			println("Piso: " + piso.numero + " con " + piso.departamentos.size + " departamentos")
		}
		println("Total asensores: " + asensores.size )
	}
	
	fun getPlantaBaja() : Piso {
		
		return pisos.get(0)
	}
	
	fun mandarAsensorAlPiso(asensor:Asensor, nroPiso:Int){
		var numeroPisoAnterior = asensor.pisoActual.numero
		
		asensor.pisoActual = pisos.get(nroPiso)
		
		println("Asensor " + asensor.numero + ": " + numeroPisoAnterior + " -> " + asensor.pisoActual.numero)
	}
	
	fun llamarAsensorAlPiso(nroPiso: Int): Asensor{
		var masCercano: Asensor = asensores.get(0)
		for(asensor in asensores){
			if( abs(asensor.pisoActual.numero - nroPiso) < abs(masCercano.pisoActual.numero - nroPiso)){
				masCercano = asensor
			}
		}
		mandarAsensorAlPiso(masCercano, nroPiso)
		
		return masCercano
	}
	
}