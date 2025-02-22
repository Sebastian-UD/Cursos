# Programa de Hola Mundo con Python

print("Hola Mundo con Python")

numero = 5-2

print(f'Numero: {numero} adios')

#LISTAS

lista = ["Hola", [1,2]]
print(lista[0])
print(lista[1])
lista.append(True)
print(lista)

#TUPLAS

mi_tupla = ("1", 2, "3")
mi_tupla2 = "4", (1,(2,3)), "6"
print(mi_tupla2[1][1])

#DESEMPAQUETADO DE TUPLAS

producto = ("P001", "Camisa", 20.00)
id, descripcion, precio = producto
print(producto)
print(id)
print(descripcion)
print(precio)

#LISTAS CON TUPLAS

precio_total = 0

productos = [
    ("P001", "Camisa", 20.00),
    ("P002", "Jeans", 30.00),
    ("P003", "Sudadera", 40.00)
]
for producto in productos:
    id, descripcion, precio = producto
    print(f'Id: {id}, descripcion: {descripcion}, precio: {precio}')
    precio_total += precio
print(precio_total)


#SETS

mi_set = {1,2,3,4,5,4}
mi_set.add(6)
mi_set.add(7)
print(mi_set)
mi_set.remove(6)
print(mi_set)

for elemento in mi_set:
    print(elemento, end=' ')

print(f'\n¿Existe el valor de 4 en el set? {6 in mi_set}')
print(len(mi_set))


#DICCIONARIOS

persona = {
    'nombre': 'Sergio',
    'edad': 30,
    'ciudad': 'Mexico'
}
print(f'Diccionario de persona: {persona}')
print(f'Nombre: {persona["nombre"]}')
print(f'Edad: {persona.get("edad")}')
print(f'Ciudad: {persona.get("ciudad")}')

persona['edad'] = 35
print(f'Edad: {persona.get("edad")}')

persona['profesion'] = 'ingeniero'
print(f'Diccionario de persona: {persona}')

del persona['ciudad']
print(f'Diccionario de persona: {persona}')

persona.pop('profesion')
print(f'Diccionario de persona: {persona}')

for llave, valor in persona.items():
    print(f'LLave: {llave}, Valor: {valor}')

for valor in persona.values():
    print(f'Valor: {valor}')

for llave in persona.keys():
    print(f'Llave: {llave}')


#COMPRENSION DE LISTAS

numeros = [1,2,3,4,5]
cuadrados = [x**2 for x in numeros]
print(cuadrados)

numeros2 = (1,2,3,4,5)
pares = [x for x in numeros2 if x%2==0]
print(pares)

nombres = ['Ana', 'Jerónimo', 'Carlos']
saludando = [f'Hola {nombre}' for nombre in nombres]
print(saludando)

