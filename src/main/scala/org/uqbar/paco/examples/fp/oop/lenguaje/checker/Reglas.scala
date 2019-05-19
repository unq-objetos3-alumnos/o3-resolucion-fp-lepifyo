package org.uqbar.paco.examples.fp.oop.lenguaje.checker

import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Division
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.ElementoPrograma
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Numero

class ReglaCheckeoBasadaEnPartialFunction(val pf : PartialFunction[ElementoPrograma, Option[Falla]]) extends ReglaCheckeo {
  override def checkear(e: ElementoPrograma) = {
    if (pf.isDefinedAt(e))
      pf.apply(e)
    else
      None
  }
}

// regla como objeto (singleton) que implementa la interaz ReglaCheckeo
object DivisionPorCero extends ReglaCheckeo {
  override def checkear(e: ElementoPrograma) = {
    e match {
      case Division(_ , Numero(0)) => Some(Falla("No se puede dividir por cero!", Error, e))  
      case _ => None
    }
  }
}

object DivisionPorCeroPF extends ReglaCheckeoBasadaEnPartialFunction({
  case e @ Division(_ , Numero(0)) => Some(Falla("No se puede dividir por cero!", Error, e))
})

