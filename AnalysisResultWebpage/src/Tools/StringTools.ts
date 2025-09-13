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
