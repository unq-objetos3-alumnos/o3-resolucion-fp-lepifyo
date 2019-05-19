package org.uqbar.paco.examples.fp.oop.lenguaje

import org.scalatest.{FunSpec, Matchers}

import org.uqbar.paco.examples.fp.oop.lenguaje.modelo._
import org.uqbar.paco.examples.fp.oop.lenguaje.interprete.Interprete

/**
 * @author jfernandes
 */
class InterpreteTestCase extends FunSpec with Matchers {

  describe("Interprete") {

    // mapa de "programa" -> "resultado esperado"
    val casos = Map[List[ElementoPrograma], ElementoPrograma](

      List(Suma(Numero(2), Numero(3))) -> Numero(5),
      List(Suma(Numero(2), Numero(0))) -> Numero(2),

      List(Resta(Numero(2), Numero(0))) -> Numero(2),
      List(Resta(Numero(5), Numero(2))) -> Numero(3),

      List(Division(Numero(2), Numero(1))) -> Numero(2),
      List(Division(Numero(20), Numero(10))) -> Numero(2),

      List(Multiplicacion(Numero(2), Numero(1))) -> Numero(2),
      List(Multiplicacion(Numero(2), Numero(0))) -> Numero(0),
      List(Multiplicacion(Numero(0), Numero(0))) -> Numero(0),

    )

    casos.foreach { case (programaDado, resultadoEsperado ) => {
      // generamos un it() por cada caso de test
      it(s"Interpretar ${programaDado} debe dar ${resultadoEsperado}") {
        val interprete = new Interprete()

        val programa = Programa(programaDado)
        val retorno = interprete.interpretar(programa)

        retorno should equal(resultadoEsperado)
      }

    }}

  }
}