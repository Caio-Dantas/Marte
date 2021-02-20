# Marte

## Princípio do Sistema de Navegação

Para o sistema de navegação da sonda funcionar adequadamente foi implementado um sistema de conversão, onde o usuário passa as informações no formato de um plano cartesiano, entretanto o programa armezana as informações como uma matriz, para isso precisamo entender algumas regras:

##### C(X,Y) = M(N-Y-1,X) = M(i,j)

- A posição da coordenada **X** corresponde a coluna _j_ .
- A posição da coordenada **Y** corresponde a linha _K_
- K é dado da seguinte forma. _K_ = tamanho do plano - linha _i_ - 1
- N é a ordem do plano.

Portanto alguns exemplos em um plano de ordem 5x5:

- C(0,0) = M(5-0-1, 0) = M(4,0)
