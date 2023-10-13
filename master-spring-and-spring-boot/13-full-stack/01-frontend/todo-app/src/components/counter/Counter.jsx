import './Counter.css'
import {PropTypes} from 'prop-types'
import {useState} from 'react'
import CounterButton from './CounterButton'

export default function Counter() {

  const [totalCount, setTotalCount] = useState(0)

  function TotalIncrementCounter(by) {
      setTotalCount(totalCount + by)
  }

  function TotalDecrementCounter(by) {
    setTotalCount(totalCount - by)
}

  function ResetTotalCount() {
    setTotalCount(0)
  }

  return(
    <>
      <span className="totalCount">{totalCount}</span>
      <CounterButton by={1} totalIncrementCounter={TotalIncrementCounter} totalDecrementCounter={TotalDecrementCounter} />
      <CounterButton by={2} totalIncrementCounter={TotalIncrementCounter} totalDecrementCounter={TotalDecrementCounter} />
      <CounterButton by={5} totalIncrementCounter={TotalIncrementCounter} totalDecrementCounter={TotalDecrementCounter} />
      <button className="resetButton" onClick={ResetTotalCount}>reset</button>
    </>
  )

}

Counter.propTypes = {
  by: PropTypes.number
}

// Counter.defaultProps = {
//   by:1
// }