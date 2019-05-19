package org.uqbar.paco.examples.fp.oop.lenguaje.checker

import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.ElementoPrograma
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Programa

/**
 * @author jfernandes
 */
class Chequeador {
  var reglas: List[ReglaCheckeo] = List()
  
  // Chequear
  
  def chequear(programa: Programa) : List[Falla] = {
    programa.elementos.flatMap(chequear(_))
  }

  protected def chequear(e:ElementoPrograma) : List[Falla] = {
    chequerElemento(e) ::: (e.hijos().flatMap { chequear _ })
  }

  protected def chequerElemento(e:ElementoPrograma) = {
    reglas flatMap { _ checkear e }
  }
  
  // configuracion de reglas
  
  def conRegla(regla: ReglaCheckeo) = {
    reglas = reglas.+:(regla)
    this
  }
  
  def in(pf : PartialFunction[ElementoPrograma, Option[Falla]]) = {
    conRegla(new ReglaCheckeoBasadaEnPartialFunction(pf))
  }

}

/**
  * Abstracción para una regla de chequeo de un elemento en particular
  * El Checkeador se encargará de evaluar esta regla con cada elemento del programa.
  */
trait ReglaCheckeo {
  def checkear(e:ElementoPrograma) : Option[Falla]
}

case class Falla(descripcion: String, gravedad: Gravedad, elemento:ElementoPrograma)

sealed class Gravedad
object Error extends Gravedad
object Warning extends Gravedad
