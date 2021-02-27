import { useEffect, useState } from "react";

const SERVER_PATH = 'http://localhost:8080';
/**
 * 
 * @param {string} path 
 */
export function useFetch(path, method = 'GET',fetchOnMount = true, isJson = true) {
    const [isLoading, setLoading] = useState(fetchOnMount);
    const [data, setData] = useState({});
    const [status, setStatus] = useState(null);
    
    const doFetch = async (body = undefined) => {
        let reqInit = { method };
        if (body) {
            reqInit.body = JSON.stringify(body);
            reqInit.headers = { 'Content-Type': 'application/json' };
        }
        setLoading(true);
        const response = await fetch(SERVER_PATH + path, reqInit);
        setLoading(false);
        setStatus(response.status);
        if (isJson) setData(await response.json());
    }

    useEffect(() => {
        if (fetchOnMount) {
            const fetchEffect = async () => {
                const response = await fetch(SERVER_PATH + path);
                setLoading(false);
                setStatus(response.status);
                setData(await response.json());
            }
            fetchEffect();
        }
    }, [path, fetchOnMount])

    return { isLoading, data, status, doFetch };
}