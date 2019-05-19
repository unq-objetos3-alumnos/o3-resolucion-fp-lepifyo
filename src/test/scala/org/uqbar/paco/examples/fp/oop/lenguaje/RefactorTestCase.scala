package org.uqbar.paco.examples.fp.oop.lenguaje

import org.scalatest.{FunSpec, Matchers}

import org.uqbar.paco.examples.fp.oop.lenguaje.checker._
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo._
import org.uqbar.paco.examples.fp.oop.lenguaje.refactor._

/**
 * @author jfernandes
 */
class RefactorTestCase extends FunSpec with Matchers {
  
  it("Refactor un programa sin tener refactors debe devolver uno igual") {
    val programa = Programa(List(Suma(Numero(4), Numero(0))))
    val refactoring = new Refactoring
    
    val refactored = refactoring.doRefactor(programa)
    
    refactored should equal(programa)
  }
  
  it("Refactor suma 0 debe reemplazar la suma por el valor izquierdo") {
    val refactoring = new Refactoring
    
    refactoring.refactor { case Suma(a, Numero(0)) => a }

    val programa = Programa(List(Suma(Numero(4), Numero(0))))
    val refactored = refactoring.doRefactor(programa)
    
    refactored should equal(Programa(List(Numero(4))))
  }
  
}