fun main(args: Array<String>){

	var tablaDePrecios: HashMap<String, Float> = hashMapOf( "impresora" to 800f, "estabilizador" to 250f, "cooler" to 50f )
	tablaDePrecios.put("teclado", 200f)
	tablaDePrecios.put("mouse", 150f)
	tablaDePrecios.put("monitor", 600f)
	tablaDePrecios.put("CPU", 1300f)
	
	
	var convertirTipoContribuyente: HashMap<String, TipoContribuyente> = hashMapOf(
			"inscripto" to TipoContribuyente.INSCRIPTOS,
			"monotributo" to TipoContribuyente.MONOTRIBUTO,
			"evasor" to TipoContribuyente.EVASOR
		)

	var productos : MutableList<String> =  mutableListOf<Articulo>()

	do{
		var input = readLine()
		if(input != ""){
			if(tablaDePrecios.get(input) == null){
				println("No tengo el precio de esto. ¡No va!")
			}else{
				productos.add(input)
			}
		}
		
	}while(input != "")

	println("Ingrese su nombre:")
	var nombreUsuario: String = readLine() 
	
	println("Ingrese su CUIT:")
	var cuitUsuario: String = readLine() 
	
	println("Indique su tipo de contribuyente (inscripto, monotributo, evasor):")
	
	var tipoContribuyente: TipoContribuyente? 
	
	do{
		var tipoContIngresadoPorelUsuario: String = readLine() 
		var tipoContValidado: TipoContribuyente? = convertirTipoContribuyente.get(tipoContIngresadoPorelUsuario)
		if(tipo != null){
			tipoContribuyente = tipoContValidado
			break
		}else{
			println("El tipo '" + tipoCont + "' no es valido, utilice: inscripto, monotributo, evasor")
		}
	}while(true)
	

	var comprador = Contribuyente(tipoContribuyente)
	var vendedor = Contribuyente(TipoContribuyente.INSCRIPTOS)
	
	
	var factura1 = vendedor.facturarleA(comprador)
	factura1.nombre = nombreUsuario
	
	
	for(nombreProducto in productos){
		var precio = tablaDePrecios.get(nombreProducto)
		if(precio == null)
			continue
		factura1.agregarProducto(nombreProducto,precio)
	}
	
	
	factura1.imprimir()
	
	
}

enum class TipoContribuyente{
	INSCRIPTOS, MONOTRIBUTO, EVASOR
}


enum class TipoFactura{
	A, B, C, E, M, TICKET_DE_MENTIRITA
}

class Contribuyente{

	
	constructor(tipo: TipoContribuyente){
		
		
				
		this.tipo = tipo
		talonarioA = mutableListOf<Factura>()
		talonarioB = mutableListOf<Factura>()
		talonarioC = mutableListOf<Factura>()
	}
	
	var tipo: TipoContribuyente;
	var talonarioA: MutableList<Factura>;
	var talonarioB: MutableList<Factura>;
	var talonarioC: MutableList<Factura>;
	var talonarioMentirita: MutableList<Factura>;
	
	fun facturarleA(otroContribuyente: Contribuyente): Factura{
	
		when(this.tipo){
			is TipoContribuyente.INSCRIPTOS -> return facturaDeInscriptoA(otroContribuyente)
			is TipoContribuyente.MONOTRIBUTO -> return facturaDeMonotributistaA(otroContribuyente)
			is TipoContribuyente.EVASOR -> return facturaDeEvasorA
		}
	
	}
	
	fun facturaDeInscriptoA(otroContribuyente: Contribuyente) : Factura{
	
		if(otroContribuyente == TipoContribuyente.INSCRIPTOS){
			var f = Factura(TipoFactura.A, 1, "18/04/2018")
			talonarioA.add(f)
		}else {
			var f = Factura(TipoFactura.B, 1, "18/04/2018")
			talonarioB.add(f)
		}
	}
	
	fun facturaDeMonotributistaA(otroContribuyente: Contribuyente) : Factura{
	
		var f = Factura(TipoFactura.C, 1, "18/04/2018")
		talonarioC.add(f)
	}
	
	fun facturaDeEvasorA(otroContribuyente: Contribuyente) : Factura{
	
		var f = Factura(TipoFactura.TICKET_DE_MENTIRITA, 1, "18/04/2018")
		talonarioMentirita.add(f)
	}
	
	
}

class Factura{

	constructor(letra: TipoFactura, numero: Int, fecha: String){
		this.letra = letra
		this.numero = numero
		this.fecha = fecha
		nombre = ""
		domicilio = ""
		cuit = ""
		articulos = mutableListOf<Articulo>()
	
	}

	var letra : 
	var numero: Int;
	var fecha: String;
	var nombre: String;
	var domicilio : String;
	var cuit: String;
	var articulos: MutableList<Articulo>;

	fun imprimir(){
		println("Factura " + letra + ":")
		println(" -> N°: " + this.numero)
		println(" -> Fecha: " + fecha)
		println(" -> nombre: " + nombre)
		println(" -> domicilio: " + domicilio)
		println(" -> cuit: " + cuit)
		println(" -> Items: ")
		var total: Float = 0f
		for( articulo in articulos ){
			println("    -> cuit: " + articulo.descripcion + " x " + articulo.cantidad + ": " + articulo.precioUnitario + " | " + (articulo.precioUnitario * articulo.cantidad))
			total += (articulo.precioUnitario * articulo.cantidad)
		}
		println(" -> Total: " + total)

	}
	fun agregarProducto(nombre: String,precio: Float){
		
		
		for(articulo in articulos){
			if(nombre == articulo.descripcion){
				articulo.cantidad++
				return
			}
		}
		
		var articulo = Articulo()
		articulo.descripcion = nombre
		articulo.precioUnitario = precio
		articulo.cantidad = 1
		
		this.articulos.add(articulo)
	
	
	}

}

class Articulo{

	constructor(){
		descripcion = ""
		precioUnitario = 0.0f
		cantidad = 0
	}

	var descripcion: String;
	var precioUnitario: Float;
	var cantidad: Int;
}













