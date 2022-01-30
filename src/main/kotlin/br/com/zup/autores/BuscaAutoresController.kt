package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any>{
        if (email.isBlank()){
            val autores = autorRepository.findAll()
            val resposta = autores.map { autor -> DetalheAutorResponse(autor) }// para cada obj / execute isso.
            return HttpResponse.ok(resposta)
        }

        val possivelAutor = autorRepository.findByEmail(email)

        if (possivelAutor.isEmpty){
            return  HttpResponse.notFound()
        }

        return HttpResponse.ok(DetalheAutorResponse(possivelAutor.get()))
    }
}