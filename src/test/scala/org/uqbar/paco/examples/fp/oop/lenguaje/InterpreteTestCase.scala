package org.uqbar.paco.examples.fp.oop.lenguaje

import org.scalatest.{FunSpec, Matchers}

import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Programa
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Numero
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Suma
import org.uqbar.paco.examples.fp.oop.lenguaje.interprete.Interprete

/**
 * @author jfernandes
 */
class InterpreteTestCase extends FunSpec with Matchers {

  describe("Interprete") {

    it("Intepretar 2 + 3 = 5") {
      val interprete = new Interprete()

      val programa = Programa(List(Suma(Numero(2), Numero(3))))
      val retorno = interprete.interpretar(programa)

      retorno shouldEqual Numero(5)
    }

  }
}