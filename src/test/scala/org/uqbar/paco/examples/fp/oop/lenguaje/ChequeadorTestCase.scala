package org.uqbar.paco.examples.fp.oop.lenguaje

import org.scalatest.{FunSpec, Matchers}

import org.uqbar.paco.examples.fp.oop.lenguaje.checker._
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo._

class ChequeadorTestCase extends FunSpec with Matchers {

  it("Checkear DivisionPorCeroPF por cero debe generar una falla"){
    val programa = Programa(List(Division(Numero(10), Numero(0))))
    
    val checkeador = new Chequeador()
    checkeador.conRegla(DivisionPorCeroPF)
    val resultado = checkeador.chequear(programa)

    resultado should equal(List(
      Falla("No se puede dividir por cero!", Error, Division(Numero(10), Numero(0)))
    ))
  }
  
  it("Checkear Division por cero como Partial Function debe generar una falla") {
    val checkeador = new Chequeador()
    
    checkeador in {
      case e @ Division(_ , Numero(0)) => Some(Falla("No se puede dividir por cero!", Error, e))
    }

    val programa = Programa(List(Division(Numero(10), Numero(0))))
    val resultado = checkeador.chequear(programa)

    resultado should equal(List(
      Falla("No se puede dividir por cero!", Error, Division(Numero(10), Numero(0)))
    ))
  }
  
  it("Checkear Multiples errores debedetectar ambos: division por cero y suma de cero") {
    val checkeador = new Chequeador()
    
    checkeador
     .in { case e @ Division(_ , Numero(0)) => Some(Falla("No se puede dividir por cero!", Error, e)) }
     .in { case e @ Suma(Numero(0), _) => Some(Falla("Suma redundante por cero!", Warning, e)) }
     .in { case e @ Suma(_, Numero(0)) => Some(Falla("Suma redundante por cero!", Warning, e)) }   
     .in { case e @ Resta(_, Numero(0)) => Some(Falla("Resta redundante por cero!", Warning, e)) }
    
    val programa = Programa(List(
      Division(Numero(10), Numero(0)),
      Suma(Numero(10), Numero(0))
    ))

    val resultado = checkeador.chequear(programa)

    resultado should equal(List(
      Falla("No se puede dividir por cero!", Error, Division(Numero(10), Numero(0))),
      Falla("Suma redundante por cero!", Warning, Suma(Numero(10), Numero(0))),
    ))

  }
  

  
}
  