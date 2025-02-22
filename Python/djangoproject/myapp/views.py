from django.http import HttpResponse, JsonResponse
from .models import Project, Task
from django.shortcuts import get_object_or_404, render, redirect
from .forms import CreateNewTask, CreateNewProject

# Create your views here.
def index(request):
    #return HttpResponse("Index page")
    title = 'Django Course'
    return render(request, 'index.html', {
        'title': title
    })

def about(request):
    #return HttpResponse('About')
    username = 'SMR'
    return render(request, 'about.html', {
        'username': username
    })

def hello(request, username):
    print(username)
    return HttpResponse("<h1>Hello %s</h1>" % username)

def projects(request):
    # Obtener todos los registros como objetos
    #projects = list(Project.objects.all()) #transforma el QuerySet a List
    #print(projects)

    # Obtener un registro como objeto filtrado por campo
    #project = Project.objects.get(id=1)
    #print(project.name)
    #project = Project.objects.get(name="aplicacion movil")
    #print(project.name)

    # Obtener todos los registros como diccionarios
    #projects = list(Project.objects.values())
    #print(projects)
    #retorna un diccionario (Json) como respuesta
    #return JsonResponse(projects[0])
    
    # El argumento safe=False permite serializar objetos que no
    # son diccionarios (Json) como respuesta
    #return JsonResponse(projects, safe=False) #retornara una lista de Jsons

    projects = list(Project.objects.all())
    return render(request, 'projects/projects.html', {
        'projects': projects
    })

def tasks(request):
    #task = Task.objects.get(title=title)
    #task = Task.objects.get(id=id)
    #task = get_object_or_404(Task, id=id) #Busca un registro y si no lo encuentra devuelve 404
    
    #return HttpResponse("task: %s" % task.title)
    tasks = Task.objects.all()
    return render(request, 'tasks/tasks.html', {
        'tasks': tasks
    })

def create_task(request):

    if request.method =='GET':
        return render(request, 'tasks/create_task.html', {
            'form': CreateNewTask()
        })
    else:
        print(request.POST['title'])
        print(request.POST['description'])
        Task.objects.create(title=request.POST['title'], description=request.POST['description'], project_id=1)
        return redirect('tasks')

def create_project(request):
    if request.method=='GET':
        return render(request, 'projects/create_project.html', {
            'form': CreateNewProject()
        })
    else:
        Project.objects.create(name=request.POST['name'])
        return redirect('projects')
    
def project_detail(request, id):
    #project = Project.objects.get(id=id)
    project = get_object_or_404(Project, id=id)
    tasks = Task.objects.filter(project_id=project.id)
    return render(request, 'projects/detail.html', {
        'project': project,
        'tasks': tasks
    })