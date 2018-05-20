
fun main(args: Array<String>){

	if(args.size == 0){
		println("No hay parametros de entrada")
	}else{
		var numero: Int? = args[0].toIntOrNull()
		
		if(numero == null){
			println("La entrada '" + args[0] + "' tiene que ser un numero")
			return
		}
		
		if(numero == 0){
			println("El numero es: 0")
		}else if(numero == 1){
			println("El numero es: 1")
		}else{
			var elMasAnterior = 0
			var elAnterior = 1
			for( i in 2..numero){
				var nuevoValor = elMasAnterior + elAnterior
				elMasAnterior = elAnterior
				elAnterior = nuevoValor
			}
			println("El numero es:" + elAnterior)
		}
	}
}