class Computadora {
    var nombreEquipo : String
    var porcentajeBateriaEnKkwHora : Int
    var conectadaARedElectrica : Boolean = false
    var apagada : Boolean = false

    constructor(nombreEquipo: String, bateria: Int) : super() {
        this.nombreEquipo = nombreEquipo
        this.porcentajeBateriaEnKkwHora = bateria
    }

    fun conectarARedElectrica () {
        if (!conectadaARedElectrica) {
            porcentajeBateriaEnKkwHora = 100
            conectadaARedElectrica = true
            apagada = false
            println("SE HA CONECTADO LA BATERIA")
        } else println("EL EQUIPO YA SE ENCUETRA CONECTADO A LA RED ELECTRICA")
    }

    fun desconectarDeRedElectrica () {
        if (!conectadaARedElectrica){ 
			println("EL EQUIPO NO SE ENCUETRA CONECTADO A LA RED ELECTRICA")
		}else { 
			conectadaARedElectrica=false
			println("SE HA DESCONECTADO LA BATERIA")
		}
    }

    fun imprimirEstado () {
        if (this.apagada) println("ESTADO: APAGADO") else println ("ESTADO: ENCENDIDO")
        if (this.conectadaARedElectrica) println("RED ELECTRICA: CONECTADA") else println ("RED ELECTRICA: NO CONECTADA")
        println("CARGA ACTUAL DE BATERIA: " + this.porcentajeBateriaEnKkwHora + "%")
    }
}

class SistemaOperativo {
    val computadora: Computadora
    var lista_aplicaciones : MutableList<Aplicacion> = arrayListOf()

    constructor(equipo: Computadora) : super() { computadora = equipo }

    fun crearAplicacion() {
        var nombre: String? = null
        while (nombre == null) {
            println("Ingrese el nombre de la Aplicacion:")
            nombre = readLine()!!.toString()
            if (nombre.toString().length <= 0) println("DEBE INGRESAR EL NOMBRE DE LA APLICACION.")
        }

        var kwHora: Int? = null
        while (kwHora == null) {
            println("Ingrese el consumo de la aplicacion:")
            kwHora = readLine()!!.toIntOrNull()
            if (kwHora.toString().length <= 0) println("DEBE INGRESAR EL CONSUMO DE LA APLICACION.")
        }

        if (!computadora.conectadaARedElectrica) 		computadora.porcentajeBateriaEnKkwHora -= kwHora
        if (computadora.porcentajeBateriaEnKkwHora > 7) {
            lista_aplicaciones.add(Aplicacion(nombre, kwHora, this))
            println("SE HA CREADO LA APLICACION")
        }
        if (computadora.porcentajeBateriaEnKkwHora>0 && computadora.porcentajeBateriaEnKkwHora<=7)  {
            lista_aplicaciones.add(Aplicacion(nombre, kwHora, this))
            println("SE HA CREADO LA APLICACION")
            for (a in lista_aplicaciones ) a.alertaDeBateria(computadora.porcentajeBateriaEnKkwHora)
        }
        if (computadora.porcentajeBateriaEnKkwHora<=0) {
            computadora.apagada = true
            lista_aplicaciones = arrayListOf()
            println("EL EQUIPO SE APAGO POR FALTA DE BATERIA.....!")
        }
    }

    fun terminarAplicacion () {
        var nombre: String? = null
        while (nombre == null) {
            println("Ingrese el nombre de la Aplicacion:")
            nombre = readLine()!!.toString()
            if (nombre.toString().length <= 0) println("DEBE INGRESAR EL NOMBRE DE LA APLICACION.")
        }

        var objApi : Aplicacion? = null
        for (a in lista_aplicaciones) if(a.nombre==nombre) objApi = a
        if (objApi == null) println("LA APLICACION NO EXISTE.")
        else lista_aplicaciones.remove(objApi); println("LA APLICACION FUE REMOVIDA.")
    }

    fun imprimirAplicacionesEnEjecucion() {
        if (lista_aplicaciones.size > 0) {
            println("LISTADO DE APLICACIONES EN EJECUCION.")
            for (a in lista_aplicaciones) println("     APLICACION: " + a.nombre + " -- CONSUMO: " + a.consumoKwHora + " kw")
        } else println("SIN APLICACIONES EN EJECUCION.")
    }
}


class Aplicacion {
    val nombre : String
    val consumoKwHora : Int
    val sistemaOperativo : SistemaOperativo

    constructor(nombre: String, consumo: Int, so: SistemaOperativo) : super() {
        this.nombre = nombre
        this.consumoKwHora = consumo
        this.sistemaOperativo = so
    }

    fun alertaDeBateria(valorKw: Int) {  println (this.nombre + " INFORMADA SOBRE ALERTA DE BATERIA: " + valorKw.toString() + " %")  }
}


fun menuClase12Factory(): Int {
    println("")
    println("Aplicaciones y recurso de bateria")
    println("1 - Crear aplicacion")
    println("2 - Terminar aplicacion")
    println("3 - Enchufar a red electrica (Carga de bateria y encender el equipo si estuviera apagado)")
    println("4 - Desconectar de red electrica")
    println("5 - Consultar estado del Equipo")
    println("INGRESE OPCION ('Y' para SALIR):")
    var opcion: Int? = readLine()!!.toIntOrNull()
    if (opcion is Int ) return opcion
    else  return 0
}


fun main(args: Array<String>) {
    val hw = Computadora("HP Notebook", 100)
    val so = SistemaOperativo(hw)

    var salir: String = "N".toLowerCase()
    while (salir != "Y".toLowerCase()) {
        var opcion: Int = menuClase12Factory()
        when (opcion) {
            1 -> if (!hw.apagada) so.crearAplicacion() else println("EL EQUIPO ESTA APAGADO!!! CONECTELO A LA RED ELECTRICA.")
            2 -> if (!hw.apagada) so.terminarAplicacion() else println("EL EQUIPO ESTA APAGADO!!! CONECTELO A LA RED ELECTRICA.")
            3 -> hw.conectarARedElectrica()
            4 -> hw.desconectarDeRedElectrica()
            5 -> { hw.imprimirEstado(); so.imprimirAplicacionesEnEjecucion(); }
            else -> {
                println("OPCION INCORRECTA ! ! ! Para salir presione 'Y':")
                salir = readLine()!!.toString().toLowerCase()
            }
        }
    }
}
