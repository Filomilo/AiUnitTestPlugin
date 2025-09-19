export const isoToDate = (isoString: string): Date => {
  return new Date(isoString)
}

export const formatDuration = (str: String) => {
  const regex = /PT(?:(\d+)H)?(?:(\d+)M)?(?:(\d+(?:\.\d+)?)S)?/
  const [, h, m, s] = str.match(regex) || []

  const hours = parseInt(h || '0', 10)
  const minutes = parseInt(m || '0', 10)
  const seconds = parseFloat(s || '0')

  const pad = (n: number, size = 2) => String(n).padStart(size, '0')

  const sec = Math.floor(seconds)
  const ms = Math.round((seconds - sec) * 1000)

  return `${pad(hours)}:${pad(minutes)}:${pad(sec)}.${pad(ms, 3)}`
}

export const FormatEscapeSequences = (str: string) => {
  return str.replace(/\\n/g, '\n').replace(/\\t/g, '\t').replace(/\\r/g, '\r')
}

export const durationStringToMs = (duration: string): number => {
  const match = duration.match(/(?:(\d+)m)?\s*(\d+(?:\.\d+)?)s/)
  const minutes = parseInt(match!![1] || '0', 10)
  const seconds = parseFloat(match!![2] || '0')

  const totalMs = minutes * 60_000 + seconds * 1000
  return totalMs
}

export const msToDurationString = (ms: number): string => {
  const minutes = Math.floor(ms / 60000)
  const seconds = ((ms % 60000) / 1000).toFixed(2)

  return `${minutes}m ${seconds}s`
}
