export default interface LogItem {
    ts: string
    msg: string
    context: {
        user: string
        ip: string
        clientType: string
        traceId: string
        object: string
        methodName: string
    }
}