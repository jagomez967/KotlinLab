class Banco {
    var nombre: String
    var direccion: String
    var lista_cuentas: MutableList<Cuenta>
    var lista_clientes: MutableList<Cliente>

    constructor(nom: String, dir: String) : super() {
        this.nombre = nom
        this.direccion = dir
        this.lista_clientes = arrayListOf()
        this.lista_cuentas = arrayListOf()
    }
	
	
	fun crearCuenta() {
	
		var dni: String? = null
		while (dni == null) {
			println("Ingrese DNI del cliente:")
			dni = readLine()!!.toString()
			if (dni.toString().length <= 0) println("DEBE INGRESAR UN DNI.")
		}
		
		var cliente = getCliente(dni)
		if( cliente == null ){
			println("No existe el cliente con DNI: " + dni)
			return
		}
	
        
		var cta: String? = null
		while (cta == null) {
			println("Ingrese numero de cuenta:")
			cta = readLine()
			if (cta == null || cta.length <= 0) println("DEBE INGRESAR UN NUMERO DE CUENTA.")
		}

		var tipo: String? = null
		while (tipo == null) {
			println("Ingrese tipo de cuenta:")
			tipo = readLine()!!.toString()
			if (tipo.toString().length <= 0) println("DEBE INGRESAR UN TIPO DE CUENTA.")
		}

		

		if (cta.toString().length > 0 && tipo.toString().length > 0) {
			var cuentaNueva = Cuenta(cta, tipo, 0.0, cliente)
			lista_cuentas.add(cuentaNueva)
			
			println("LA CUENTA SE HA CREADO EXITOSAMENTE.")
		} else println("NO SE HA CREADO LA CUENTA!!!!!!!!!!!!!!!!!!!!!!!!!")
    }
	
	fun crearCliente() {
        var doc: String? = null
        while (doc == null) {
            println("Ingrese DNI:")
            doc = readLine()!!.toString()
            if (doc.toString().length <= 0) println("DEBE INGRESAR UN NUMERO DE DNI.")
        }

        var nom: String? = null
        while (nom == null) {
            println("Ingrese nombre:")
            nom = readLine()!!.toString()
            if (nom.toString().length <= 0) println("DEBE INGRESAR EL NOMBRE DEL CLIENTE.")
        }

        if (nom.toString().length > 0 && doc.toString().length > 0) {
		
			if(existeCliente(doc)){
				println("EL CLIENTE YA EXISTE")
				return
			}
			var nuevoCliente = Cliente(nom, doc)
			this.lista_clientes.add(nuevoCliente)
            println("EL CLIENTE SE HA CREADO EXITOSAMENTE.")
        } else {
            println("NO SE HA CREADO EL CLIENTE !!!!!!!!!!!!!!!!!!!!!!!!!")
        }
    }
	
	fun getCliente(doc: String): Cliente?{
		for( cliente in lista_clientes){
			if(cliente.DNI == doc)	return cliente
		}
		return null
	}
	
	fun existeCliente(doc: String): Boolean{
		return getCliente(doc) != null
	}
	
	fun getCuenta(doc: String): Cuenta?{
		for( cuenta in lista_cuentas){
			if(cuenta.numero == doc)	return cuenta
		}
		return null
	}
	

    fun imprimirCuentas() {

		println("INFORMACION DE CUENTAS.")
		var total = 0.0
		for (c in lista_cuentas) {
			total = total + c.saldo
		}
		println("Saldo Totalizado de Cuentas: " + total)
    }
	
	fun realizarMovimiento() {
		var cta: String? = null
		while (cta == null) {
			println("Ingrese numero de cuenta:")
			cta = readLine()
			if (cta == null || cta.length == 0) println("DEBE INGRESAR UN NUMERO DE CUENTA.")
		}

		var cuenta = getCuenta(cta)
		if(cuenta == null){
			println("No existe la cuenta " + cuenta)
			return
		}
		
		var tipoMov: String = ""
		var salir = false
		while (!salir) {
			println("Ingrese tipo de Movimiento: Debito -->D / Credito -->C")
			tipoMov = readLine()!!.toString()
			if (tipoMov.toLowerCase() == "D".toLowerCase() || tipoMov.toLowerCase() == "C".toLowerCase()) salir = true
			else println("DEBE INGRESAR UN TIPO DE MOVIMIENTO: Debito -->D / Credito -->C")
		}

		var valor: Double? = null
		while (valor == null) {
			println("Ingrese un valor:")
			valor = readLine()!!.toDoubleOrNull()
			if (valor.toString().length <= 0) println("DEBE INGRESAR UN VALOR MAYOR A 0(CERO).")
		}

		if (valor > 0) {
			if (tipoMov.toLowerCase()  == "D".toLowerCase()) {
				if (cuenta.saldo >= valor) 
					cuenta.saldo -= valor
				else 
					println("SALDO INSUFICIENTE")
			}else {
				cuenta.saldo += valor
			}
			
		}
    }
	
	fun cerrarCuenta() {
	
		var cta: String? = null
		while (cta == null) {
			println("Ingrese numero de cuenta:")
			cta = readLine()
			if (cta == null || cta.length <= 0) println("DEBE INGRESAR UN NUMERO DE CUENTA.")
		}

		var cuenta = getCuenta(cta)
		if(cuenta == null){
			println("No existe la cuenta " + cuenta)
			return
		}
	
		var cliente = cuenta.cliente
		lista_cuentas.remove(cuenta)
		cliente.desasociarCuenta(cta)
		println("SE HA BORRADO LA CUENTA")
        
    }
	
	
	
	fun inactivarCliente() {
	
		var dni: String? = null
		while (dni == null) {
			println("Ingrese DNI del cliente:")
			dni = readLine()!!.toString()
			if (dni.toString().length <= 0) println("DEBE INGRESAR UN DNI.")
		}
		
		var cliente = getCliente(dni)
		if( cliente == null ){
			println("No existe el cliente con DNI: " + dni)
			return
		}
	
		lista_clientes.remove(cliente)
		for(cuenta in cliente.lista_cuentas){
			lista_cuentas.remove(cuenta)
		}
	}
	
	
	fun imprimirCliente() {
	
		var dni: String? = null
		while (dni == null) {
			println("Ingrese DNI del cliente:")
			dni = readLine()!!.toString()
			if (dni.toString().length <= 0) println("DEBE INGRESAR UN DNI.")
		}
		
		var cliente = getCliente(dni)
		if( cliente == null ){
			println("No existe el cliente con DNI: " + dni)
			return
		}
	
		println("Cliente: " + cliente.DNI + "-" + cliente.nombre)
		var total : Double = 0.0
		for (cu in cliente.lista_cuentas) {
			println("Cuenta Numero: " + cu.numero + " --- Tipo Cuenta: " + cu.tipo + " Saldo: " + cu.saldo)
			total = total + cu.saldo
		}
		println("Totalizado del Cliente: " + total)
	
    }
	
	
}

class Cliente {
    var nombre: String
    var DNI: String
    var lista_cuentas: MutableList<Cuenta>

    constructor(nom: String, dni: String) : super() {
        this.nombre = nom
        this.DNI = dni
        this.lista_cuentas =  arrayListOf()
    }


    fun asociarCuenta(cuenta : Cuenta){
		var duplicada = getCuenta(cuenta.numero)
		if(duplicada == null){
			lista_cuentas.add(cuenta)
		}else{
			println("El cliente ya tiene esa cuenta")
		}
	}
	
	fun desasociarCuenta(nro: String){
		var cuenta = getCuenta(nro)
		if(cuenta == null){
			println("El no tiene esa cuenta")
		}else{
			lista_cuentas.remove(cuenta)
		}
	}

	fun getCuenta(doc: String): Cuenta?{
		for( cuenta in lista_cuentas){
			if(cuenta.numero == doc)	return cuenta
		}
		return null
	}

    

    
}

class Cuenta {
    var numero: String
    var tipo: String
    var saldo: Double
    var cliente: Cliente

    constructor(nro: String, tipo: String, saldo: Double, cli: Cliente) {
        this.numero = nro
        this.tipo = tipo
        this.saldo = saldo
        this.cliente = cli
    }
}

fun main(args: Array<String>) {

    var banco = Banco("Galicia", "Medrano 951")

    var salir: String = "N".toLowerCase()

    while (salir != "Y".toLowerCase()) {
        var opcion: Int = menuClase8()
        when (opcion) {
            0 -> System.exit(1)
            1 -> banco.crearCuenta()
            2 -> banco.realizarMovimiento() 
            3 -> banco.imprimirCuentas()
            4 -> banco.cerrarCuenta() 
            5 -> banco.crearCliente()
            6 -> banco.inactivarCliente() 
            7 -> banco.imprimirCliente() 
            else -> {
                println("OPCION INCORRECTA ! ! ! Para salir presione 'Y':")
                salir = readLine()!!.toString().toLowerCase()
            }
        }
    }
}


fun menuClase8(): Int {
    println("")
    println("BANCO Y CUENTAS")
    println("1....Crear Cuenta")
    println("2....Realizar Movimiento con Cuenta")
    println("3....Presentar Informacion de Cuentas")
    println("4....Cerrar Cuenta")
    println("")
    println("CLIENTES")
    println("5....Crear Cliente")
    println("6....Inactivar Cliente")
    println("7....Presentar Informacion de Cliente")
    println("")
    println("INGRESE OPCION ('S' para SALIR):")
    var opcion: Int? = readLine()!!.toIntOrNull()
    if (opcion is Int ) return opcion
    else  return 0
}
