package org.uqbar.paco.examples.fp.oop.lenguaje.interprete

import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Programa
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.ElementoPrograma
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Suma
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Numero
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Division

/**
 * @author jfernandes
 */
class Interprete {
  
  def interpretar(programa:Programa) = {
    programa.elementos.map { interpretarElemento _ } last
  }
  
  def interpretarElemento(elemento:ElementoPrograma) : ElementoPrograma = {
    elemento match {
      case Suma(Numero(a),Numero(b)) => Numero(a + b)
      case Division(Numero(a),Numero(b)) => Numero(a / b)
      case _ => throw new UnsupportedOperationException("No se como interpreter " + elemento)
    }
  }
  
}