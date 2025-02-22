import mysql.connector

zona_fit_db = mysql.connector.connect(
    host='localhost',
    user='root',
    password='root',
    database='zona_fit_db'
)

cursor = zona_fit_db.cursor()

# SELECT
def consultar():
    cursor.execute("SELECT * FROM cliente")
    resultado = cursor.fetchall()

    for cliente in resultado:
        print(cliente)

# CREATE
def crear():
    sentencia_sql = "INSERT INTO cliente(nombre, apellido, membresia) VALUES(%s, %s, %s)"
    valores = ("Victor", "Ramos", 500)
    cursor.execute(sentencia_sql, valores)
    zona_fit_db.commit() # Guardar los cambios en la bd

# UPDATE
def actualizar():
    sentencia_sql = "UPDATE cliente SET nombre=%s WHERE id = %s"
    valores = ("Carlos", 11)
    cursor.execute(sentencia_sql, valores) # Guardar los cambios en la bd
    zona_fit_db.commit()

# DELETE
def eliminar():
    sentencia_sql = "DELETE from cliente WHERE id = %s"
    valor = (11,)
    cursor.execute(sentencia_sql, valor)
    zona_fit_db.commit()



if __name__ == '__main__':
    consultar()
    #crear()
    #actualizar()
    #eliminar()

    # CERRAR CONEXION
    zona_fit_db.close()
