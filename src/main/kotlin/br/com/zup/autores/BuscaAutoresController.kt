package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun lista(): HttpResponse<List<DetalheAutorResponse>>{
        val autores = autorRepository.findAll()
        val resposta = autores.map { autor -> DetalheAutorResponse(autor) }// para cada obj / execute isso.
        return HttpResponse.ok(resposta)
    }
}