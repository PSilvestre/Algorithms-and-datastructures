/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import dataStructures.Iterator;
import fitness.Fitness;
import fitness.FitnessClass;
import fitness.GroupNonModifiable;
import fitness.PerformedActivity;
import fitness.UserNonModifiable;
import fitness.exceptions.ActivityAlreadyExistsException;
import fitness.exceptions.ActivityDoesNotExistException;
import fitness.exceptions.AthleteHasntTrainedException;
import fitness.exceptions.EmptyGroupException;
import fitness.exceptions.GroupAlreadyExistsException;
import fitness.exceptions.GroupDoesNotExistException;
import fitness.exceptions.InvalidOperationException;
import fitness.exceptions.InvalidTimeException;
import fitness.exceptions.InvalidValueException;
import fitness.exceptions.NoGroupsException;
import fitness.exceptions.UserAlreadyExistsException;
import fitness.exceptions.UserAlreadyInGroupException;
import fitness.exceptions.UserDoesNotExistException;
import fitness.exceptions.UserNotInGroupException;

public class Main {
	
	/**
	 * Exception messages.
	 */
	private static final String ACTIVITY_DOES_NOT_EXIST = "Atividade inexistente.";
	private static final String ACTIVITY_ALREADY_EXISTS = "Atividade existente.";
	private static final String INVALID_MET = "MET invalido.";
	private static final String INVALID_TIME = "Tempo invalido.";
	private static final String NO_GROUPS = "Nao existem grupos.";
	private static final String INVALID_VALUE = "Valores invalidos.";
	private static final String INVALID_OPERATION = "Opcao invalida.";
	private static final String USER_NOT_IN_GROUP = "Atleta nao pertence ao grupo.";
	private static final String USER_ALREADY_IN_GROUP = "Atleta ja tem grupo.";
	private static final String GROUP_DOES_NOT_EXIST = "Grupo inexistente.";
	private static final String EMPTY_GROUP = "Grupo nao tem adesoes.";
	private static final String GROUP_ALREADY_EXISTS = "Grupo existente.";
	private static final String USER_DOES_NOT_EXIST = "Atleta inexistente.";
	private static final String USER_ALREADY_EXISTS = "Atleta existente.";
	private static final String ATHLETE_HASNT_TRAINED = "Atleta sem treinos.";
	private static final String INVALID_NUM_STEPS = "Numero de passos invalido.";
	
	/**
	 * Output Success messages.
	 */
	private static final String GROUP_FORFEIT_SUCCESSFUL = "Desistencia realizada com sucesso.";
	private static final String ADDITTION_TO_GROUP_SUCCESS = "Adesao realizada com sucesso.";
	private static final String GROUP_CREATED_SUCCESSFULLY = "Grupo criado com sucesso.";
	private static final String STEPS_UPDATED_SUCCESSFULLY = "Atualizacao de passos com sucesso.";
	private static final String WORKOUT_ADDED_SUCCESSFULLY = "Treino adicionado com sucesso.";
	private static final String SUCCESSFUL_ACTIVITY_CREATION = "Atividade criada com sucesso.";
	private static final String SUCCESSFUL_USER_REMOVAL = "Atleta removido com sucesso.";
	private static final String SUCCESSFUL_USER_UPDATE = "Atleta atualizado com sucesso.";
	private static final String SUCCESSFUL_USER_INSERTION = "Insercao de atleta com sucesso.";
	private static final String ERROR = "Erro...";
	private static final String SAVING_EXITING = "Gravando e terminando...";
	
	/**
	 * File to save state in or load from.
	 */
	private static final String FILE_NAME = "state.txt";
	
	private enum Command {
		IU, // Insert user
		UU, // Update user
		RU, // Remove user
		CU, // Check user
		IA, // Create activity
		AW, // Add workout
		CW, // Check workouts
		AS, // Update steps
		CG, // Create group
		AG, // Add user to group
		DG, // Forfeit from group
		IG, //Check group
		LG, // List group
		LC, // List walkers
		LW, //List warriors
		XS, // Exit
		UNKNOWN
	}

	public static void main(String[] args) {
		Fitness fitness = loadState();
		if(fitness == null)
			fitness = new FitnessClass();
		commands(fitness);
		saveState(fitness);
	}

	
	private static void commands(Fitness fitness) {
		Scanner in = new Scanner(System.in);
		Command comm = getCommand(in);

		while (!comm.equals(Command.XS)) {
			switch (comm) {
			case IU:
				insertUser(in, fitness);
				break;
			case UU:
				updateUser(in, fitness);
				break;
			case RU:
				removeUser(in, fitness);
				break;
			case CU:
				checkUser(in, fitness);
				break;
			case IA:
				createWorkout(in, fitness);
				break;
			case AW:
				addWorkoutToUser(in, fitness);
				break;
			case CW:
				checkWorkouts(in, fitness);
				break;
			case AS:
				updateSteps(in, fitness);
				break;
			case IG:
				createGroup(in, fitness);
				break;
			case AG:
				addUserToGroup(in, fitness);
				break;
			case DG:
				forfeitFromGroup(in, fitness);
				break;
			case CG:
				checkGroup(in, fitness);
				break;
			case LG:
				listGroup(in, fitness);
				break;
			case LC:
				listWalkers(in, fitness);
				break;
			case LW:
				listWarriors(in, fitness);
				break;
			case UNKNOWN:
				System.out.println(ERROR);
				break;
			default:
				System.out.println(ERROR);
				break;
			}
			System.out.println();
			comm = getCommand(in);

		}
		System.out.println(SAVING_EXITING);
		System.out.println();
		in.close();
	}

	private static void insertUser(Scanner in, Fitness fit){
		String id = in.next().trim();
		int weight = in.nextInt();
		int height = in.nextInt();
		int age = in.nextInt();
		String gender = Character.toString(in.next().charAt(0));
		String name = in.nextLine().trim();
		
		try{
			fit.addUser(id, gender, weight, height, age, name);
			System.out.println(SUCCESSFUL_USER_INSERTION);
		}catch(InvalidValueException e){
			System.out.println(INVALID_VALUE);
		}catch(UserAlreadyExistsException e){
			System.out.println(USER_ALREADY_EXISTS);
		}
	}
	
	private static void updateUser(Scanner in, Fitness fit){
		String id = in.next().trim();
		int weight = in.nextInt();
		int height = in.nextInt();
		int age = in.nextInt();
		try{
			fit.updateUserInfo(id, weight, height, age);
			System.out.println(SUCCESSFUL_USER_UPDATE);
		}catch(UserDoesNotExistException e){
			System.out.println(USER_DOES_NOT_EXIST);
		}catch(InvalidValueException e){
			System.out.println(INVALID_VALUE);
		}
	}
	
	private static void removeUser(Scanner in, Fitness fit){
		String id = in.next().trim();
		try{
			fit.removeUser(id);
			System.out.println(SUCCESSFUL_USER_REMOVAL);
		}catch(UserDoesNotExistException e){
			System.out.println(USER_DOES_NOT_EXIST);
		}
	}
	
	private static void checkUser(Scanner in, Fitness fit){
		String id = in.next().trim();
		try{
			UserNonModifiable u = fit.getUser(id);
			String result = u.getName() + ": " + u.getGender() + ", " + u.getWeight() + " kg, " + u.getAge() + " anos, " + u.getCalorieCount() + " cal, " + u.getSteps() + " ps";
			if(u.isInGroup())
				result += " (" + u.getGroup().getName() + ")";
			System.out.println(result);
		}catch(UserDoesNotExistException e){
			System.out.println(USER_DOES_NOT_EXIST);
		}
	}
	
	private static void createWorkout(Scanner in, Fitness fit) {
		String actId = in.next().trim();
		int MET = in.nextInt();
		String name = in.nextLine().trim();
		try{
			fit.createActivity(actId, MET, name);
			System.out.println(SUCCESSFUL_ACTIVITY_CREATION);
		}catch(ActivityAlreadyExistsException e){
			System.out.println(ACTIVITY_ALREADY_EXISTS);
		}catch(InvalidValueException e){
			System.out.println(INVALID_MET);
		}
	}
	
	private static void addWorkoutToUser(Scanner in, Fitness fit) {
		String userId = in.next().trim();
		String actId = in.next().trim();
		int duration = in.nextInt();
		
		try{
			fit.addWorkoutToUser(userId, actId, duration);
			System.out.println(WORKOUT_ADDED_SUCCESSFULLY);
		}catch(InvalidTimeException e){
			System.out.println(INVALID_TIME);
		}catch(UserDoesNotExistException e){
			System.out.println(USER_DOES_NOT_EXIST);
		}catch(ActivityDoesNotExistException e){
			System.out.println(ACTIVITY_DOES_NOT_EXIST);
		}
	}
	
	private static void checkWorkouts(Scanner in, Fitness fit) {
		String userId = in.next().trim();
		char type = in.next().charAt(0);
		try{
			Iterator<PerformedActivity> it = fit.getWorkouts(userId, type);
			PerformedActivity a;
			while(it.hasNext()){
				a = it.next();
				System.out.println(a.getName() + " " + a.getCalories() + " cal");
			
			}
		}catch(InvalidOperationException e){
			System.out.println(INVALID_OPERATION);
		}catch(UserDoesNotExistException e){
			System.out.println(USER_DOES_NOT_EXIST);
		} catch (AthleteHasntTrainedException e) {
			System.out.println(ATHLETE_HASNT_TRAINED);
		}
	}
	
	private static void updateSteps(Scanner in, Fitness fit) {
		String userId = in.next().trim();
		int steps = in.nextInt();
		try{
			fit.updateSteps(userId, steps);
			System.out.println(STEPS_UPDATED_SUCCESSFULLY);
		}catch(UserDoesNotExistException e){
			System.out.println(USER_DOES_NOT_EXIST);
		}catch(InvalidValueException e){
			System.out.println(INVALID_NUM_STEPS);
		}
	}
	
	private static void createGroup(Scanner in, Fitness fit) {
		String groupId = in.next().trim();
		String name = in.next().trim();
		try{
			fit.createGroup(groupId, name);
			System.out.println(GROUP_CREATED_SUCCESSFULLY);
		}catch(GroupAlreadyExistsException e){
			System.out.println(GROUP_ALREADY_EXISTS);
		}
	}
	
	private static void addUserToGroup(Scanner in, Fitness fit) {
		String userId = in.next().trim();
		String groupId = in.next().trim();
		try{
			fit.addUserToGroup(userId, groupId);
			System.out.println(ADDITTION_TO_GROUP_SUCCESS);
		}catch(UserDoesNotExistException e){
			System.out.println(USER_DOES_NOT_EXIST);
		} catch (GroupDoesNotExistException e) {
			System.out.println(GROUP_DOES_NOT_EXIST);
		} catch (UserAlreadyInGroupException e) {
			System.out.println(USER_ALREADY_IN_GROUP);
		}
	}
	
	private static void forfeitFromGroup(Scanner in, Fitness fit) {
		String userId = in.next().trim();
		String groupId = in.next().trim();
		try{
			fit.forfeitFromGroup(userId, groupId);
			System.out.println(GROUP_FORFEIT_SUCCESSFUL);
		}catch(UserDoesNotExistException e){
			System.out.println(USER_DOES_NOT_EXIST);
		} catch (GroupDoesNotExistException e) {
			System.out.println(GROUP_DOES_NOT_EXIST);
		} catch (UserNotInGroupException e) {
			System.out.println(USER_NOT_IN_GROUP);
		}
	}
	
	private static void checkGroup(Scanner in, Fitness fit) {
		String groupId = in.next().trim();
		try{
			GroupNonModifiable g = fit.getGroup(groupId);
			System.out.println("Grupo " + g.getName() + ": " + g.getCumulativeCalories() + " cal, " + g.getCumulativeSteps() + " ps");
		}catch(GroupDoesNotExistException e){
			System.out.println(GROUP_DOES_NOT_EXIST);
		}
	}
	
	private static void listGroup(Scanner in, Fitness fit) {
		String groupId = in.next().trim();
		try{
			Iterator<UserNonModifiable> members = fit.listGroup(groupId);
			UserNonModifiable m;
			while(members.hasNext()){
				m = members.next();
				System.out.println(m.getName() + ": " + m.getGender() + ", " + m.getWeight() + " kg, " + m.getAge() + " anos, " + m.getCalorieCount() + " cal, " + m.getSteps() + " ps");
			}
		}catch(GroupDoesNotExistException e){
			System.out.println(GROUP_DOES_NOT_EXIST);
		} catch (EmptyGroupException e) {
			System.out.println(EMPTY_GROUP);
		}
	}
	
	private static void listWalkers(Scanner in, Fitness fit) {
		try{
			Iterator<GroupNonModifiable> walkers = fit.listWalkers();
			while(walkers.hasNext()){
				GroupNonModifiable g = walkers.next();
				System.out.println("Grupo " + g.getName()  + ": " + g.getCumulativeCalories() + " cal, " + g.getCumulativeSteps() + " ps");
			}
			
		}catch(NoGroupsException e){
			System.out.println(NO_GROUPS);
		}
	}
	
	private static void listWarriors(Scanner in, Fitness fit) {
		try{
			Iterator<GroupNonModifiable> warriors = fit.listWalkers();
			while(warriors.hasNext()){
				GroupNonModifiable g = warriors.next();
				System.out.println("Grupo " + g.getName()  + ": " + g.getCumulativeCalories() + " cal, " + g.getCumulativeSteps() + " ps");
			}
			
		}catch(NoGroupsException e){
			System.out.println(NO_GROUPS);
		}
	}

	private static Command getCommand(Scanner in) {
		try {
			return Command.valueOf(in.next().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Command.UNKNOWN;
		}
	}
	
	private static Fitness loadState(){
		Fitness fit = null;
		try{
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(FILE_NAME));
			fit = (Fitness) file.readObject();
			file.close();
		}catch(FileNotFoundException e){
			//Do nothing
		} catch(IOException e){
			//Do nothing
		} catch (ClassNotFoundException e) {
			//Do nothing
		}
		return fit;
	}
	
	private static void saveState(Fitness fit){
		try{
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			file.writeObject(fit);
			file.flush();
			file.close();
		
		
		}catch(IOException e){
			//Do nothing
		}
	}
}