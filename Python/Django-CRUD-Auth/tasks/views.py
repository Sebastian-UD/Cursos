from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm
from django.contrib.auth.models import User
from django.contrib.auth import login, logout, authenticate
from django.db import IntegrityError
from.forms import TaskForm
from .models import Task
from django.utils import timezone
from django.contrib.auth.decorators import login_required

# Create your views here.
def home(request):
    return render(request, 'home.html')

def singup(request):
    if request.method=='GET':
        return render(request, 'signup.html', {
            'form': UserCreationForm
        })
    else:
        print(request.POST)
        if request.POST['password1'] == request.POST['password2']:
            try:
                user = User.objects.create_user(username=request.POST['username'], password=request.POST['password1'])
                user.save()
                login(request, user)
                return redirect('tasks')
            except IntegrityError:
                return render(request, 'signup.html', {
                    'form': UserCreationForm,
                    'error': 'El ususario ya existe'
                })
        return render(request, 'signup.html', {
            'form': UserCreationForm,
            'error': 'Las contraseñas no coinciden'
        })         

def signout(request):
    logout(request)
    return redirect('home')

def signin(request):
    if request.method == 'GET':
        return render(request, 'signin.html', {
            'form': AuthenticationForm
        })
    else:
        user = authenticate(request, username=request.POST['username'], password=request.POST['password'])
        if user is None:
             return render(request, 'signin.html', {
                'form': AuthenticationForm,
                'error': 'Username or password incorrect'
            })
        else:
            login(request, user)
            return redirect('tasks')

@login_required
def tasks(request):
    print(request.user.id)
    # tasks = Task.objects.filter(user=request.user)
    # tasks = Task.objects.filter(user=request.user.id)
    tasks = Task.objects.filter(user_id=request.user.id, dateCompleted__isnull=True)
    return render(request, 'tasks.html', {
        'tasks': tasks
    })

@login_required
def tasks_completed(request):
    print(request.user.id)
    tasks = Task.objects.filter(user_id=request.user.id, dateCompleted__isnull=False).order_by('-dateCompleted')
    return render(request, 'tasks.html', {
        'tasks': tasks
    })

@login_required
def create_task(request):
    if request.method=='GET':
        return render(request, 'create_task.html', {
            'form': TaskForm
        })
    else:
        try:
            form = TaskForm(request.POST)
            new_task = form.save(commit=False) # crear el objeto sin guardar en la db
            new_task.user = request.user
            new_task.save() # guardar en la bd
            return redirect('tasks')
        except ValueError:
            return render(request, 'create_task.html', {
            'form': TaskForm,
            'error': 'Please provide valid data'
        })

@login_required
def task_detail(request, task_id):
    if request.method=='GET':
        # task = Task.objects.get(pk=task_id)
        task = get_object_or_404(Task, id=task_id, user_id=request.user.id)
        form = TaskForm(instance=task)
        return render(request, 'task_detail.html', {
            'task': task,
            'form': form
        })
    else:
        try:
            task = get_object_or_404(Task, id=task_id, user_id=request.user.id)
            form = TaskForm(request.POST, instance=task)
            form.save()
            return redirect('tasks')
        except ValueError:
            return render(request, 'task_detail.html', {
            'task': task,
            'form': form,
            'error': 'Error updating task'
        })

@login_required
def complete_task(request, task_id):
    task = get_object_or_404(Task, id=task_id, user_id=request.user.id)
    if request.method=='POST':
        if task.dateCompleted is None:
            task.dateCompleted = timezone.now()
            task.save()
        else:
            task.dateCompleted = None
            task.save()
        return redirect('tasks')

@login_required
def delete_task(request, task_id):
    task = get_object_or_404(Task, id=task_id, user_id=request.user.id)
    if request.method=='POST':
        task.delete()
        return redirect('tasks')
