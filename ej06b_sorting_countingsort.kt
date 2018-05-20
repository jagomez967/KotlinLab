fun main(args: Array<String>){

	var numeros = IntArray(args.size)

	for( i in 0..(args.size -1)){
		var num = args[i].toIntOrNull()
		if(num == null){
			println("Numero invalido: " + num)
			return
		}
		numeros[i]=num
	}
	
	var numerosOrdenados = countingSort(numeros)
	
	for( num in numerosOrdenados){
		print(" " + num )
	}
	print("\n")

}

fun countingSort(numeros: IntArray) : IntArray{

	
	var numeroMasAlto = 0
	
	for( numero in numeros){
		if(numero > numeroMasAlto){
			numeroMasAlto = numero
		}
	}
	
	var arrayContador = IntArray(numeroMasAlto + 1)
	
	for( indice in 0..(arrayContador.size - 1)){
		arrayContador[indice] = 0
	}
	
	for(numero in numeros){
		arrayContador[numero]++
	}
	
	var numerosOrdenados = IntArray(numeros.size)
	var posicionUltimoNumeroOrdenado = 0
	for(indiceContador in 0..(arrayContador.size - 1) ){
		var cantidad = arrayContador[indiceContador]
		if(cantidad > 0){
			for( noMeImporta in 1..cantidad){
				numerosOrdenados[posicionUltimoNumeroOrdenado++] = indiceContador
			}
		}
	}
	
	
	return numerosOrdenados
}
