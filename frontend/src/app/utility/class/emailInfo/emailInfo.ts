export class EmailInfo {
    toEmail!: string;
    subject!: string;
    body!: string;

    constructor(toEmail: string, subject: string, body: string) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }
}