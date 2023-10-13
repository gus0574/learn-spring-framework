import {useState} from 'react'
import {PropTypes} from 'prop-types'

export default function CounterButton({by, totalIncrementCounter, totalDecrementCounter}) {
  const [count, setCount] = useState(0)

  function incrementCounterFunction() {
    setCount(count+by)
    totalIncrementCounter(by)
  }

  function decrementCounterFunction() {
      setCount(count-by)
      totalDecrementCounter(by)
  }

  return(
    <div className="CounterButton">
      <span className="count">{count}</span>
      <div>
        <span>
        <button className="counterButton" onClick={incrementCounterFunction}>+{by}</button>
        </span>
        <span>
          <button className="counterButton" onClick={decrementCounterFunction}>-{by}</button>
        </span>
      </div>
    </div>
  )
}

CounterButton.propTypes = {
  by: PropTypes.number
}