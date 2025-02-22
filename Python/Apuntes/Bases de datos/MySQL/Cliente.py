class Cliente:
    def __init__(self, id=None, nombre=None, apellido=None, membresia=None):
        self.__id = id
        self.__nombre = nombre
        self.__apellido = apellido
        self.__membresia = membresia

    def __str__(self):
        return (f'Id: {self.__id}, Nombre: {self.__nombre}, '
                f'Apellido: {self.__apellido}, Membresia: {self.__membresia}')

    def set_id(self, id):
        self.__id = id

    def set_nombre(self, nombre):
        self.__nombre = nombre

    def set_apellido(self, apellido):
        self.__apellido = apellido

    def set_membresia(self, membresia):
        self.__membresia = membresia