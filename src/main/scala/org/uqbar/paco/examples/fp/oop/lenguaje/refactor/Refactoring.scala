package org.uqbar.paco.examples.fp.oop.lenguaje.refactor

import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.ElementoPrograma
import org.uqbar.paco.examples.fp.oop.lenguaje.modelo.Programa

class Refactoring {
  type Refactor = PartialFunction[ElementoPrograma, ElementoPrograma]
  
  var refactors : List[Refactor] = List()
  
  def refactor(refactor : Refactor) = {
    refactors  = refactors :+ refactor
    this
  }
  
  def doRefactor(programa:Programa) = {
    programa.copy(elementos = programa.elementos.map(e=> doRefactorElemento(e)))
  }
  
  def doRefactorElemento(elemento: ElementoPrograma) : ElementoPrograma = {
    refactors.foldLeft(elemento) ((e, refactor) =>
      if (refactor.isDefinedAt(e))
           refactor.apply(e)
      else
         e
    )
  }
  
}