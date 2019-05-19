package org.uqbar.paco.examples.fp.oop.lenguaje.modelo

case class Programa(elementos : List[ElementoPrograma])

trait ElementoPrograma {
  def hijos() : List[ElementoPrograma] = Nil
}

case class Numero(valor:Int) extends ElementoPrograma

sealed class OperacionNumerica(op1:Numero, op2:Numero) extends ElementoPrograma {
  override def hijos() = List(op1, op2)
}
case class Suma(op1:Numero, op2:Numero) extends OperacionNumerica(op1, op2)
case class Resta(op1:Numero, op2:Numero) extends OperacionNumerica(op1, op2)
case class Division(dividendo:Numero, divisor:Numero) extends OperacionNumerica(divisor, dividendo)
case class Multiplicacion(op1:Numero, op2:Numero) extends OperacionNumerica(op1, op2)



