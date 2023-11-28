package org.squirrelframework.foundation.fsm;

import java.util.List;
import java.util.Set;

import org.squirrelframework.foundation.component.SquirrelComponent;

/**
 * <p><b>State</b> The basic unit that composes a state machine. A state machine can be in one state at 
 * any particular time.</p>
 * <p><b>Entry Action</b> An activity executed when entering the state</p>
 * <p><b>Entry Action</b> An activity executed when entering the state</p>
 * <p><b>Final State</b> A state which represents the completion of the state machine.</p>
 * 
 * @author Henry.He
 *
 * @param <T> type of State Machine
 * @param <S> type of State
 * @param <E> type of Event
 * @param <C> type of Context
 */
public interface ImmutableState<T extends StateMachine<T, S, E, C>, S, E, C> extends Visitable, SquirrelComponent
{
	/**
	 * Enters this state by its history depending on its
	 * <code>HistoryType</code>. The <code>Entry</code> method has to be called
	 * already.
	 *
	 * @param stateContext
	 *            the state context.
	 * @return the active state. (depends on this states<code>HistoryType</code>)
	 */
	ImmutableState<T, S, E, C> enterByHistory(StateContext<T, S, E, C> stateContext);


	/**
	 * Enters this state is deep mode: mode if there is one.
	 *
	 * @param stateContext
	 *            the event context.
	 * @return the active state.
	 */
	ImmutableState<T, S, E, C> enterDeep(StateContext<T, S, E, C> stateContext);


	/**
	 * Enters this state is shallow mode: The entry action is executed and the
	 * initial state is entered in shallow mode if there is one.
	 * @param stateContext
	 * @return child state entered by shadow
	 */
	ImmutableState<T, S, E, C> enterShallow(StateContext<T, S, E, C> stateContext);


	/**
	 * Entry state with state context
	 * @param stateContext
	 */
	void entry(ImmutableState<T, S, E, C> source, ImmutableState<T, S, E, C> target, StateContext<T, S, E, C> stateContext);


	/**
	 * Exit state with state context
	 * @param stateContext
	 */
	void exit(StateContext<T, S, E, C> stateContext);


	/**
	 * @return events that can be accepted by this state
	 */
	Set<E> getAcceptableEvents();


	/**
	 * @return All transitions start from this state
	 */
	List<ImmutableTransition<T, S, E, C>> getAllTransitions();


	/**
	 * @return child states
	 */
	List<ImmutableState<T, S, E, C>> getChildStates();


	/**
	 * @return child states composite type
	 */
	StateCompositeType getCompositeType();


	/**
	 * @return Activities executed when entering the state
	 */
	List<Action<T, S, E, C>> getEntryActions();


	/**
	 * @return Activities executed when exiting the state
	 */
	List<Action<T, S, E, C>> getExitActions();


	/**
	 * @return Historical type of state
	 */
	HistoryType getHistoryType();


	/**
	 * @return initial child state
	 */
	ImmutableState<T, S, E, C> getInitialState();


	/**
	 * @return hierarchy state level
	 */
	int getLevel();


	/**
	 * @return parent state
	 */
	ImmutableState<T, S, E, C> getParentState();


	String getPath();


	/**
	 * @return state id
	 */
	S getStateId();


	ImmutableState<T, S, E, C> getThis();


	/**
	 * @param event 
	 * @return Transitions triggered by event
	 */
	List<ImmutableTransition<T, S, E, C>> getTransitions(E event);


	/**
	 * @return whether state has child states
	 */
	boolean hasChildStates();


	/**
	 * Notify transitions when receiving event.
	 * @param stateContext
	 */
	void internalFire(StateContext<T, S, E, C> stateContext);


	boolean isChildStateOf(ImmutableState<T, S, E, C> input);


	/**
	 * @return whether current state is final state
	 */
	boolean isFinalState();


	/**
	 * @return whether child states composite type is parallel
	 */
	boolean isParallelState();


	boolean isRegion();


	/**
	 * @return whether state is root state
	 */
	boolean isRootState();


	/**
	 * Verify state correctness
	 */
	void verify();
}
